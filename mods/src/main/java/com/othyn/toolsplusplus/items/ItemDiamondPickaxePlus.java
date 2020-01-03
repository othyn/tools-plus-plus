package com.othyn.toolsplusplus.items;

import com.othyn.toolsplusplus.ToolsPlusPlus;

import net.minecraft.item.ItemPickaxe;

public class ItemDiamondPickaxePlus extends ItemPickaxe {

	public ItemDiamondPickaxePlus(ToolMaterial material, String unlocalizedName, String registryName) {
		super(material);

		setTranslationKey(ToolsPlusPlus.MODID + "." + unlocalizedName);
		setRegistryName(registryName);

		setCreativeTab(ToolsPlusPlus.PLUS_TAB);
	}
}
