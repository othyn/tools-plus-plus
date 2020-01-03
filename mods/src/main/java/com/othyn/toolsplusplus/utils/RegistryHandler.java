package com.othyn.toolsplusplus.utils;

import com.othyn.toolsplusplus.items.ItemDiamondPickaxePlus;
import com.othyn.toolsplusplus.items.ItemIronBar;
import com.othyn.toolsplusplus.material.PlusMaterials;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void registerItems(Register<Item> event) {
		final Item[] items = {
			new ItemIronBar("ironBar", "iron_bar"),
			
			new ItemDiamondPickaxePlus(PlusMaterials.TOOL_DIAMOND_PLUS, "diamondPickaxePlus", "diamond_pickaxe_plus")
		};

		event.getRegistry().registerAll(items);
	}
}
