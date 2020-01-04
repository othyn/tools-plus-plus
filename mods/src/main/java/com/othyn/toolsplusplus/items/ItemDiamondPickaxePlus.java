package com.othyn.toolsplusplus.items;

import java.util.Set;

import com.google.common.collect.Sets;
import com.othyn.toolsplusplus.ToolsPlusPlus;
import com.othyn.toolsplusplus.utils.AreaOfEffectHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class ItemDiamondPickaxePlus extends ItemPickaxe
{
	private static final Set<Block> PickaxeBlocks = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE);
	private static final Set<Material> PickaxeMats = Sets.newHashSet(Material.ANVIL, Material.GLASS, Material.ICE, Material.IRON, Material.PACKED_ICE, Material.PISTON, Material.ROCK);
	
	public ItemDiamondPickaxePlus(ToolMaterial material, String unlocalizedName, String registryName)
	{
		super(material);

		setTranslationKey(ToolsPlusPlus.MODID + "." + unlocalizedName);
		setRegistryName(registryName);

		setCreativeTab(ToolsPlusPlus.PLUS_TAB);
	}
    
    public boolean isEffective(ItemStack stack, IBlockState state)
    {
        for (String type : getToolClasses(stack)) {
            if (state.getBlock().isToolEffective(type, state)) {
                return true;
            }
        }

        return PickaxeMats.contains(state.getMaterial()) || PickaxeBlocks.contains(state.getBlock());
    }
	
    private void breakArea(ItemStack stack, EntityPlayer player, BlockPos posHit, BlockPos posStart, BlockPos posEnd)
    {
        for (int xPos = posStart.getX(); xPos <= posEnd.getX(); xPos++) {
            for (int yPos = posStart.getY(); yPos <= posEnd.getY(); yPos++) {
                for (int zPos = posStart.getZ(); zPos <= posEnd.getZ(); zPos++) {
                    if (xPos == posHit.getX() && yPos == posHit.getY() && zPos == posHit.getZ()) {
                        continue;
                    }

                    if (!super.onBlockStartBreak(stack, new BlockPos(xPos, yPos, zPos), player) && !stack.isEmpty()) {
                    	AreaOfEffectHandler.breakBlock(stack, player.world, player, new BlockPos(xPos, yPos, zPos), posHit);
                    }
                }
            }
        }
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player)
    {
        //Block being mined
        RayTraceResult ray = rayTrace(player.world, player, false);
        if (ray == null) {
            return super.onBlockStartBreak(stack, pos, player);
        }

        //Calculate area to break
        BlockPos[] positions = AreaOfEffectHandler.getBreakArea(stack, pos, ray.sideHit, player);
        BlockPos start = positions[0];
        BlockPos end = positions[1];

        //LogHelper.info("Breaking blocks from " + start.toString() + " to " + end.toString());
        breakArea(stack, player, pos, start, end);

        return super.onBlockStartBreak(stack, pos, player);
    }
}
