package com.othyn.toolsplusplus.tabs;

import com.othyn.toolsplusplus.ToolsPlusPlus;
import com.othyn.toolsplusplus.init.PlusItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
 
public class PlusTab extends CreativeTabs {

    public PlusTab(String name) {
        super(ToolsPlusPlus.MODID + "." + name);
    }
 
    @SideOnly(Side.CLIENT)
	@Override
	public ItemStack createIcon() {
        return new ItemStack(PlusItems.IRON_BAR);
	}
}
