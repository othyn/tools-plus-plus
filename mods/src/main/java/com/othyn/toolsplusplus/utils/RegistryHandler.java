package com.othyn.toolsplusplus.utils;

import com.othyn.toolsplusplus.items.ItemDiamondPickaxePlus;
import com.othyn.toolsplusplus.items.ItemIronBar;
import com.othyn.toolsplusplus.material.PlusMaterials;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This is the place where you actually define the Items.
 */
@EventBusSubscriber
public class RegistryHandler {
    /**
     * This is the method where the items actually get declared, their names in the
     * registry given, their localised names and tool materials. This so the actual
     * item classes can be reused if needs be.
     */
    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        final Item[] items = { new ItemIronBar("ironBar", "iron_bar"), new ItemDiamondPickaxePlus(
                PlusMaterials.TOOL_DIAMOND_PLUS, "diamondPickaxePlus", "diamond_pickaxe_plus") };

        event.getRegistry().registerAll(items);
    }
}
