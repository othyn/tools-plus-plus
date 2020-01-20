package com.othyn.toolsplusplus.utils;

import com.othyn.toolsplusplus.ToolsPlusPlus;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

/**
 * This class is an extract (with tweaks) of the Area of Effect handler for
 * SparksHammers, learned a lot from reading through this implementation and
 * certainly would not have gotten half as far if not for this:
 * https://github.com/thebrightspark/SparksHammers/blob/master/src/main/java/brightspark/sparkshammers/util/CommonUtils.java
 */
public class AreaOfEffectHandler {
    /**
     * Finds out the area that should be mined based on the coords of the player and
     * the source block.
     */
    public static BlockPos[] getBreakArea(BlockPos blockPos, EnumFacing sideHit, EntityPlayer player) {
        // The area to mine the original block, plus one in each axis to make a 3x3x1
        // grid.The Z axis is determined from the values of the width and height. To
        // keep the z axis on the same plane as the mined block, set the mineDepth - the
        // Z offset - to zero.
        int mineWidth = 1;
        int mineHeight = 1;
        int mineDepth = 0;

        // To keep the z axis on the same plane as the mined block, set the Z offset to
        // zero.
        BlockPos start = blockPos.offset(sideHit, mineDepth);
        BlockPos end = blockPos.offset(sideHit, mineDepth);

        // Not sure what this accomplishes, but I assume it is to do with offsetting the
        // start and end positions based on if the player is stood at eye level with the
        // block and hitting its vertical sides.
        if (!player.capabilities.isFlying && sideHit != EnumFacing.UP && sideHit != EnumFacing.DOWN && mineHeight > 1) {
            start = start.up(mineHeight - 1);
            end = end.up(mineHeight - 1);
        }

        /**
         * Determine the coordinates that the block area should be mined around based on
         * the side (face) of the block that was hit.
         */
        switch (sideHit) {
        case DOWN:
        case UP:
            EnumFacing facing = EnumFacing.fromAngle(player.getRotationYawHead());
            switch (facing) {
            case WEST:
            case EAST:
                start = start.add(-mineHeight, 0, -mineWidth);
                end = end.add(mineHeight, 0, mineWidth);
                break;
            case NORTH:
            case SOUTH:
            default:
                start = start.add(-mineWidth, 0, -mineHeight);
                end = end.add(mineWidth, 0, mineHeight);
                break;
            }
            break;
        case NORTH:
        case SOUTH:
            // Z axis
            start = start.add(-mineWidth, -mineHeight, 0);
            end = end.add(mineWidth, mineHeight, 0);
            break;
        case WEST:
        case EAST:
            // X axis
            start = start.add(0, -mineHeight, -mineWidth);
            end = end.add(0, mineHeight, mineWidth);
            break;
        }

        return new BlockPos[] { start, end };
    }

    /**
     * Breaks the block, if the tool can, at the desired block location within the
     * world, handing off block harvest logic and tool processing (damage, etc.)
     */
    public static void breakBlock(ItemStack stack, World world, EntityPlayer player, BlockPos blockPos,
            BlockPos refBlockPos) {
        IBlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();

        if (!breakBlockChecks(stack, world, blockPos, blockState)) {
            return;
        }

        IBlockState refBlockState = world.getBlockState(refBlockPos);
        float refStrength = ForgeHooks.blockStrength(refBlockState, player, world, refBlockPos);
        float strength = ForgeHooks.blockStrength(blockState, player, world, blockPos);

        // only harvestable blocks that aren't impossibly slow to harvest
        if (!ForgeHooks.canHarvestBlock(block, player, world, blockPos) || refStrength / strength > 10f) {
            ToolsPlusPlus.logger.info("T++ breakBlock: Cannot harvest block.");
            return;
        }

        breakBlockAction(stack, world, player, blockPos, block, blockState);
    }

    /**
     * Perform validation that the block can be broken and that the tool can break
     * it.
     */
    private static boolean breakBlockChecks(ItemStack stack, World world, BlockPos blockPos, IBlockState block) {
        // prevent calling that stuff for air blocks, could lead to unexpected behaviour
        // since it fires events
        if (world.isAirBlock(blockPos)) {
            return false;
        }

        // check if the block can be broken, since extra block breaks shouldn't
        // instantly break stuff like obsidian
        // or precious ores you can't harvest while mining stone
        // only effective materials
        if (!((stack.getItem()).canHarvestBlock(block))) {
            ToolsPlusPlus.logger.info("T++ breakBlockChecks: Cannot harvest block: " + block.toString());
            return false;
        }

        return true;
    }

    /**
     * Perform the action of actually breaking the block, providing xp, damaging the
     * tool, etc. everything that would happen on a standard block-break.
     */
    private static void breakBlockAction(ItemStack stack, World world, EntityPlayer player, BlockPos blockPos,
            Block block, IBlockState blockState) {
        // From this point on it's clear that the player CAN break the block

        if (player.capabilities.isCreativeMode) {
            block.onBlockHarvested(world, blockPos, blockState, player);

            if (block.removedByPlayer(blockState, world, blockPos, player, false)) {
                block.onBlockHarvested(world, blockPos, blockState, player);
            }

            // send update to client
            if (!world.isRemote) {
                ((EntityPlayerMP) player).connection.sendPacket(new SPacketBlockChange(world, blockPos));
            }

            return;
        }

        // callback to the tool the player uses. Called on both sides. This damages the
        // tool n stuff.
        // stack.onBlockDestroyed(world, blockState, blockPos, player);

        // server sided handling
        if (!world.isRemote) {
            // send the blockbreak event
            int xp = ForgeHooks.onBlockBreakEvent(world, ((EntityPlayerMP) player).interactionManager.getGameType(),
                    (EntityPlayerMP) player, blockPos);

            if (xp == -1) {
                return;
            }

            // server side we reproduce ItemInWorldManager.tryHarvestBlock

            TileEntity te = world.getTileEntity(blockPos);

            // ItemInWorldManager.removeBlock

            // boolean is if block can be harvested, checked above
            if (block.removedByPlayer(blockState, world, blockPos, player, true)) {
                block.onBlockHarvested(world, blockPos, blockState, player);
                block.harvestBlock(world, player, blockPos, blockState, te, stack);
                block.dropXpOnBlockBreak(world, blockPos, xp);
            }

            // always send block update to client
            ((EntityPlayerMP) player).connection.sendPacket(new SPacketBlockChange(world, blockPos));
        } else {
            // client sided handling

            // client side we do a "this block has been clicked on long enough to be broken"
            // call. This should not send any new packets
            // the code above, executed on the server, sends a block-updates that give us
            // the correct state of the block we destroy.

            // following code can be found in PlayerControllerMP.onPlayerDestroyBlock
            // world.playEvent(2001, blockPos, Block.getStateId(blockState));
            if (block.removedByPlayer(blockState, world, blockPos, player, true)) {
                block.onBlockHarvested(world, blockPos, blockState, player);
            }

            // callback to the tool
            // stack.onBlockDestroyed(world, blockState, blockPos, player);

            if (stack.getCount() == 0 && stack == player.getHeldItemMainhand()) {
                ForgeEventFactory.onPlayerDestroyItem(player, stack, EnumHand.MAIN_HAND);
                player.setHeldItem(EnumHand.MAIN_HAND, null);
            }

            // send an update to the server, so we get an update back
            Minecraft.getMinecraft().getConnection()
                    .sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos,
                            Minecraft.getMinecraft().objectMouseOver.sideHit));
        }
    }
}
