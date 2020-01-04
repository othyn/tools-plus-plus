package com.othyn.toolsplusplus.items;

import com.othyn.toolsplusplus.ToolsPlusPlus;

import net.minecraft.item.Item;

public class ItemIronBar extends Item
{
	public ItemIronBar(String unlocalizedName, String registryName)
	{
		setTranslationKey(ToolsPlusPlus.MODID + "." + unlocalizedName);
		setRegistryName(registryName);

		setCreativeTab(ToolsPlusPlus.PLUS_TAB);
	}
}
