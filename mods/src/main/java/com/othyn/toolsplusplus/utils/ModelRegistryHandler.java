package com.othyn.toolsplusplus.utils;

import com.othyn.toolsplusplus.init.PlusItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Handles the Forge registry registration of objects (Items).
 */
@EventBusSubscriber(Side.CLIENT)
public class ModelRegistryHandler {
    /**
     * Registers the models onto the Forge registry, declare items defined in
     * PlusItems here to actually register them into the Forge Item registry, and
     * thus the game.
     */
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        registerModel(PlusItems.IRON_BAR);

        registerModel(PlusItems.DIAMOND_PICKAXE_PLUS);
    }

    /**
     * Just a helper wrapper method to take the name of the Item and set that as the
     * resource location for image assets and to register the model Forge resource
     * location against.
     */
    private static void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
