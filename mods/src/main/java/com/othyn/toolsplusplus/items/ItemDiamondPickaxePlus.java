package com.othyn.toolsplusplus.items;

import com.othyn.toolsplusplus.ToolsPlusPlus;
import com.othyn.toolsplusplus.utils.AreaOfEffectHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

/**
 * This is the creation of an enhanced Diamond Pickaxe that has increased
 * durability and mines in 3x3x1 grids instead of 1x1x1. Lots of help on this
 * one from the SparksHammers mod:
 * https://github.com/thebrightspark/SparksHammers/blob/master/src/main/java/brightspark/sparkshammers/item/ItemAOE.java
 */
public class ItemDiamondPickaxePlus extends ItemPickaxe {
    /**
     * Constructor method, same as class name. Declare that the item needs to
     * inherit its material from the parent tool, in this case, a diamond pickaxe -
     * this defines the durability, uses, etc. Then what the name of the item is in
     * FQDN style, this is what minecraft uses internally as reference to the item -
     * e.g. minecraft.diamond_pickaxe. Then what the item is in the Forge registry,
     * which is the item collection, and assigning it to the creative tab so it can
     * be used in creative mode in game - good for testing.
     *
     * @param unlocalizedName The plain name of the item, e.g.
     *                        minecraft.diamond_pickaxe.
     * @param registryName    The name to be found in the Forge registry.
     */
    public ItemDiamondPickaxePlus(ToolMaterial material, String unlocalizedName, String registryName) {
        super(material);

        setTranslationKey(ToolsPlusPlus.MODID + "." + unlocalizedName);
        setRegistryName(registryName);

        setCreativeTab(ToolsPlusPlus.PLUS_TAB);
    }

    /**
     * Break the area within a set of 3D coordinates, this is called once the broken
     * block has been determined and validated that the pickaxe can break it, the
     * area is then sent to this method to actually break the required blocks. A
     * simple loop through the 3 coordinate axis, covering all the blocks within and
     * breaking the found block.
     */
    private void breakArea(ItemStack stack, EntityPlayer player, BlockPos posHit, BlockPos posStart, BlockPos posEnd) {
        for (int xPos = posStart.getX(); xPos <= posEnd.getX(); xPos++) {
            for (int yPos = posStart.getY(); yPos <= posEnd.getY(); yPos++) {
                for (int zPos = posStart.getZ(); zPos <= posEnd.getZ(); zPos++) {
                    if (xPos == posHit.getX() && yPos == posHit.getY() && zPos == posHit.getZ()) {
                        continue;
                    }

                    if (!super.onBlockStartBreak(stack, new BlockPos(xPos, yPos, zPos), player) && !stack.isEmpty()) {
                        AreaOfEffectHandler.breakBlock(stack, player.world, player, new BlockPos(xPos, yPos, zPos),
                                posHit);
                    }
                }
            }
        }
    }

    /**
     * Override the event that is called before the block is broken, as an injection
     * point for when we can calculate what block is being broken and the area
     * around that block that will need to be broken, before finally breaking the
     * area around the block and handing the event back to the parent handler for
     * the default minecraft actions to then follow.
     */
    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
        // Determine the block being mined
        RayTraceResult ray = rayTrace(player.world, player, false);

        if (ray == null) {
            return super.onBlockStartBreak(stack, pos, player);
        }

        // Calculate area to break
        BlockPos[] positions = AreaOfEffectHandler.getBreakArea(pos, ray.sideHit, player);
        BlockPos start = positions[0];
        BlockPos end = positions[1];

        ToolsPlusPlus.logger.info("T++ PICKAXE: Breaking blocks from " + start.toString() + " to " + end.toString());

        // Break the area around the block
        breakArea(stack, player, pos, start, end);

        return super.onBlockStartBreak(stack, pos, player);
    }
}
