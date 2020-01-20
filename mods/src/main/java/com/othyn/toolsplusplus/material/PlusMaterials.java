package com.othyn.toolsplusplus.material;

import com.othyn.toolsplusplus.ToolsPlusPlus;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Defining any custom materials that tools can take on as their; harvest level,
 * durability, efficiency, damage and enchantability.
 */
public class PlusMaterials {
    // Values increased from base Diamond values found in Item.ToolMaterial.DIAMOND
    // (view declaration of net.minecraft.item.Item.ToolMaterial class)
    public static final ToolMaterial TOOL_DIAMOND_PLUS = EnumHelper
            .addToolMaterial(ToolsPlusPlus.MODID + ":" + "tool_plus", 5, 3000, 12.0F, 5.0F, 15);
}
