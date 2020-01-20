package com.othyn.toolsplusplus.init;

import com.othyn.toolsplusplus.ToolsPlusPlus;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

/**
 * This class will act as the ObjectHolder, this loads the mod declared
 * Minecraft Items declared into the registry and allows them to be accessible
 * via this class.
 *
 * Across the mods item naming, I've tried to keep it consistent with Minecrafts
 * internal naming schema.
 *
 * ObjectHolder: ObjectHolder can be used to automatically populate public
 * static final fields with entries from the registry. These values can then be
 * referred within mod code directly.
 */
@ObjectHolder(ToolsPlusPlus.MODID)
public class PlusItems {
    public static final Item IRON_BAR = null;

    public static final Item DIAMOND_PICKAXE_PLUS = null;
//	public static final Item DIAMOND_SHOVEL_PLUS = null;
//	public static final Item DIAMOND_AXE_PLUS = null;
//	public static final Item DIAMOND_HOE_PLUS = null;
}
