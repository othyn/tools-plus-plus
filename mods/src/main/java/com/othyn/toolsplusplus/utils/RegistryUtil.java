package com.othyn.toolsplusplus.utils;

import com.othyn.toolsplusplus.ToolsPlusPlus;

import net.minecraft.item.Item;

public final class RegistryUtil {
	public static Item setItemName(Item item, String name) {
		item.setRegistryName(ToolsPlusPlus.MODID, name);
		item.setTranslationKey(ToolsPlusPlus.MODID + "." + name);
		return item;
	}
}
