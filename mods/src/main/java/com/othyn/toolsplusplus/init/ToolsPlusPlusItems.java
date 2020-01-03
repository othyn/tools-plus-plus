package com.othyn.toolsplusplus.init;

import com.othyn.toolsplusplus.ToolsPlusPlus;
import com.othyn.toolsplusplus.utils.RegistryUtil;
import com.othyn.toolsplusplus.items.ItemPlusDiamondPickaxe;
import com.othyn.toolsplusplus.material.ToolsPlusPlusMaterials;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(ToolsPlusPlus.MODID)
public class ToolsPlusPlusItems {

	public static final Item PLUS_IRON_BAR = new Item();
	
	public static final Item ITEM_PLUS_DIAMOND_PICKAXE = new ItemPlusDiamondPickaxe(ToolsPlusPlusMaterials.TOOLS_PLUS_PLUS_TOOL);
	
	@EventBusSubscriber(modid = ToolsPlusPlus.MODID)
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerItems(Register<Item> event) {
			final Item[] items = {
					RegistryUtil.setItemName(PLUS_IRON_BAR, "plus_iron_bar").setCreativeTab(ToolsPlusPlus.TOOLS_PLUS_PLUS_TAB),
					RegistryUtil.setItemName(ITEM_PLUS_DIAMOND_PICKAXE, "item_plus_diamond_pickaxe").setCreativeTab(ToolsPlusPlus.TOOLS_PLUS_PLUS_TAB)
			};

			event.getRegistry().registerAll(items);
		}
	}
}
