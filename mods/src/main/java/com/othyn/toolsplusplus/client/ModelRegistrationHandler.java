package com.othyn.toolsplusplus.client;

import com.othyn.toolsplusplus.ToolsPlusPlus;
import com.othyn.toolsplusplus.init.ToolsPlusPlusItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT, modid = ToolsPlusPlus.MODID)

public class ModelRegistrationHandler {
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		registerModel(ToolsPlusPlusItems.PLUS_IRON_BAR, 0);
	}

	private static void registerModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, 
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
