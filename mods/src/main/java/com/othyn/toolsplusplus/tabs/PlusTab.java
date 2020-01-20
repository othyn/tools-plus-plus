package com.othyn.toolsplusplus.tabs;

import com.othyn.toolsplusplus.ToolsPlusPlus;
import com.othyn.toolsplusplus.init.PlusItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Custom creative mode tab declaration, so that the items defined within the
 * mod can be easily accessed and found in game for creative mode players.
 */
public class PlusTab extends CreativeTabs {
    /**
     * Define the tab internal name (Forge registry name?).
     */
    public PlusTab(String name) {
        super(ToolsPlusPlus.MODID + "." + name);
    }

    /**
     * On the client side only (if the mod is being run on the server, it ignores
     * this function declaration), this overrides the item image that the tab should
     * display in the tab bar and sets it to the iron bar.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(PlusItems.IRON_BAR);
    }
}
