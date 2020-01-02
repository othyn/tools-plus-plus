package com.othyn.toolsplusplus.tabs;

import com.othyn.toolsplusplus.ToolsPlusPlus;
import com.othyn.toolsplusplus.init.ToolsPlusPlusItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
 
public class ToolsPlusPlusTab extends CreativeTabs {
 
    public ToolsPlusPlusTab() {
        super(ToolsPlusPlus.MODID);
    }
 
    @SideOnly(Side.CLIENT)
    
	@Override
	public ItemStack createIcon() {
        return new ItemStack(ToolsPlusPlusItems.PLUS_IRON_BAR);
	}
 
}
