package com.othyn.toolsplusplus.items;

import com.othyn.toolsplusplus.ToolsPlusPlus;

import net.minecraft.item.ItemHoe;

/**
 * This is the creation of an enhanced Diamond Hoe that has increased durability
 * and effects.
 */
public class ItemDiamondHoePlus extends ItemHoe {
    /**
     * Constructor method, same as class name. Declare that the item needs to
     * inherit its material from the parent tool, in this case, a diamond hoe - this
     * defines the durability, uses, etc. Then what the name of the item is in FQDN
     * style, this is what minecraft uses internally as reference to the item - e.g.
     * minecraft.diamond_hoe. Then what the item is in the Forge registry, which is
     * the item collection, and assigning it to the creative tab so it can be used
     * in creative mode in game - good for testing.
     *
     * @param unlocalizedName The plain name of the item, e.g.
     *                        minecraft.diamond_hoe.
     * @param registryName    The name to be found in the Forge registry.
     */
    public ItemDiamondHoePlus(ToolMaterial material, String unlocalizedName, String registryName) {
        super(material);

        setTranslationKey(ToolsPlusPlus.MODID + "." + unlocalizedName);
        setRegistryName(registryName);

        setCreativeTab(ToolsPlusPlus.PLUS_TAB);
    }
}
