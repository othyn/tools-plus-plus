package com.othyn.toolsplusplus.items;

import com.othyn.toolsplusplus.ToolsPlusPlus;

import net.minecraft.item.Item;

/**
 * This is the creation of a very basic material item used to make more complex
 * tools. This Item has no functionality and is purely used to be crafted and to
 * craft other more complex items.
 */
public class ItemIronBar extends Item {
    /**
     * Declare what the name of the item is in FQDN style, this is what minecraft
     * uses internally as reference to the item - e.g. minecraft.diamond_pickaxe.
     * Then what the item is in the Forge registry, which is the item collection,
     * and assigning it to the creative tab so it can be used in creative mode in
     * game - good for testing.
     *
     * @param unlocalizedName The plain name of the item, e.g.
     *                        minecraft.diamond_pickaxe.
     * @param registryName    The name to be found in the Forge registry.
     */
    public ItemIronBar(String unlocalizedName, String registryName) {
        setTranslationKey(ToolsPlusPlus.MODID + "." + unlocalizedName);
        setRegistryName(registryName);

        setCreativeTab(ToolsPlusPlus.PLUS_TAB);
    }
}
