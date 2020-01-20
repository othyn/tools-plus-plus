package com.othyn.toolsplusplus.items;

import com.othyn.toolsplusplus.ToolsPlusPlus;

import net.minecraft.item.ItemAxe;

/**
 * This is the creation of an enhanced Diamond Axe that has increased durability
 * and effects.
 */
public class ItemDiamondAxePlus extends ItemAxe {
    /**
     * Constructor method, same as class name. Declare that the item needs to
     * inherit its material from the parent tool, in this case, a diamond axe - this
     * defines the durability, uses, etc. Then what the name of the item is in FQDN
     * style, this is what minecraft uses internally as reference to the item - e.g.
     * minecraft.diamond_axe. Then what the item is in the Forge registry, which is
     * the item collection, and assigning it to the creative tab so it can be used
     * in creative mode in game - good for testing.
     *
     * @param material        The material the axe will posses.
     * @param damage          The damage the axe will do.
     * @param speed           The speed the axe will recover.
     * @param unlocalizedName The plain name of the item, e.g.
     *                        minecraft.diamond_axe.
     * @param registryName    The name to be found in the Forge registry.
     */
    public ItemDiamondAxePlus(ToolMaterial material, float damage, float speed, String unlocalizedName,
            String registryName) {
        super(material, damage, speed);

        setTranslationKey(ToolsPlusPlus.MODID + "." + unlocalizedName);
        setRegistryName(registryName);

        setCreativeTab(ToolsPlusPlus.PLUS_TAB);
    }

    /**
     * Helper constructor for setting the values just above Diamond. Default
     * ItemAxe.ATTACK_DAMAGES for diamond is 8.0F (goto definition on ItemAxe
     * extension as always) and default ItemAxe.ATTACK_SPEEDS for diamond is -3.0F.
     *
     * @param material
     * @param unlocalizedName
     * @param registryName
     */
    public ItemDiamondAxePlus(ToolMaterial material, String unlocalizedName, String registryName) {
        this(material, 10.0F, -2.5F, unlocalizedName, registryName);
    }
}
