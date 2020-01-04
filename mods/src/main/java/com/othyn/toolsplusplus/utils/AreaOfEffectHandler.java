package com.othyn.toolsplusplus.utils;

import com.othyn.toolsplusplus.items.ItemDiamondPickaxePlus;

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

public class AreaOfEffectHandler
{	
	public static BlockPos[] getBreakArea(ItemStack hammerStack, BlockPos pos, EnumFacing sideHit, EntityPlayer player)
    {
        int mineWidth = 1;
        int mineHeight = 1;

        BlockPos start = pos.offset(sideHit, 0);
        BlockPos end = pos.offset(sideHit, 0);

        if (!player.capabilities.isFlying && sideHit != EnumFacing.UP && sideHit != EnumFacing.DOWN && mineHeight > 1) {
            start = start.up(mineHeight - 1);
            end = end.up(mineHeight - 1);
        }

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
                //Z axis
                start = start.add(-mineWidth, -mineHeight, 0);
                end = end.add(mineWidth, mineHeight, 0);
                break;
            case WEST:
            case EAST:
                //X axis
                start = start.add(0, -mineHeight, -mineWidth);
                end = end.add(0, mineHeight, mineWidth);
                break;
        }

        return new BlockPos[] {start, end};
    }
	
	public static void breakBlock(ItemStack stack, World world, EntityPlayer player, BlockPos blockPos, BlockPos refBlockPos)
    {
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
        	return;
        }

        breakBlockAction(stack, world, player, blockPos, block, blockState);
    }
	
	private static boolean breakBlockChecks(ItemStack stack, World world, BlockPos blockPos, IBlockState block)
    {
        // prevent calling that stuff for air blocks, could lead to unexpected behaviour since it fires events
        if (world.isAirBlock(blockPos)) {
        	return false;
        }

        // check if the block can be broken, since extra block breaks shouldn't instantly break stuff like obsidian
        // or precious ores you can't harvest while mining stone
        // only effective materials
        if (!((ItemDiamondPickaxePlus) stack.getItem()).isEffective(stack, block)) {
        	return false;
        }

        return true;
    }
	
	private static void breakBlockAction(ItemStack stack, World world, EntityPlayer player, BlockPos blockPos, Block block, IBlockState blockState)
    {
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

        // callback to the tool the player uses. Called on both sides. This damages the tool n stuff.
        stack.onBlockDestroyed(world, blockState, blockPos, player);

        // server sided handling
        if (!world.isRemote) {
            // send the blockbreak event
            int xp = ForgeHooks.onBlockBreakEvent(world, ((EntityPlayerMP) player).interactionManager.getGameType(), (EntityPlayerMP) player, blockPos);
            
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
        	
            // client side we do a "this block has been clicked on long enough to be broken" call. This should not send any new packets
            // the code above, executed on the server, sends a block-updates that give us the correct state of the block we destroy.

            // following code can be found in PlayerControllerMP.onPlayerDestroyBlock
            //world.playEvent(2001, blockPos, Block.getStateId(blockState));
            if (block.removedByPlayer(blockState, world, blockPos, player, true)) {
                block.onBlockHarvested(world, blockPos, blockState, player);
            }
            
            // callback to the tool
            stack.onBlockDestroyed(world, blockState, blockPos, player);

            if (stack.getCount() == 0 && stack == player.getHeldItemMainhand()) {
                ForgeEventFactory.onPlayerDestroyItem(player, stack, EnumHand.MAIN_HAND);
                player.setHeldItem(EnumHand.MAIN_HAND, null);
            }

            // send an update to the server, so we get an update back
            Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, Minecraft.getMinecraft().objectMouseOver.sideHit));
        }
    }
}
