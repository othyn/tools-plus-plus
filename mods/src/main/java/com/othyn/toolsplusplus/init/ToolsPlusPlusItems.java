package com.othyn.toolsplusplus.init;

import com.othyn.toolsplusplus.ToolsPlusPlus;
import com.othyn.toolsplusplus.utils.RegistryUtil;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(ToolsPlusPlus.MODID)
public class ToolsPlusPlusItems {

	public static final Item PLUS_IRON_BAR = null;
	
	@EventBusSubscriber(modid = ToolsPlusPlus.MODID)
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerItems(Register<Item> event) {
			final Item[] items = {
					RegistryUtil.setItemName(new Item(), "plus_iron_bar").setCreativeTab(CreativeTabs.MISC)
			};

			event.getRegistry().registerAll(items);
		}
	}
}
