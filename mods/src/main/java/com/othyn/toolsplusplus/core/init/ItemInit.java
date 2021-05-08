package com.othyn.toolsplusplus.core.init;

import com.othyn.toolsplusplus.ToolsPlusPlus;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ToolsPlusPlus.MOD_ID);
	
	public static final RegistryObject<Item> AXE_PLUS_PLUS = ITEMS.register("axe_plus_plus", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
}
