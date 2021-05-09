package com.othyn.toolsplusplus.common.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraftforge.common.ToolType;

public class ShovelPlusPlus extends ShovelItem {
	
    public ShovelPlusPlus(IItemTier material) {
    	// Second param is the additional attack damage
    	// Third param is the additional attack speed
        super(material, 10, 10, new Properties().tab(ItemGroup.TAB_TOOLS).addToolType(ToolType.SHOVEL, material.getLevel()));
    }
}
