package com.othyn.toolsplusplus;

import org.apache.logging.log4j.Logger;

import com.othyn.toolsplusplus.tabs.PlusTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ToolsPlusPlus.MODID, name = ToolsPlusPlus.NAME, version = ToolsPlusPlus.VERSION, acceptedMinecraftVersions = ToolsPlusPlus.MC_VERSION)

// TODO - Make the axe functionality be so that it can cut down whole trees. I
// think do this by onBlockBreak check surrounding blocks, if one is wood break
// it and check 3x3x3 surrounding the broken wooden block to see if that is also
// wood or leaves, break it. Between each action, pause for 250ms. The obvious
// side effect would mean if there are connected trees, it will harvest it all.

/**
 * Main mod entry point and handler.
 */
public class ToolsPlusPlus {
    /**
     * Declarations of static mod content, fixtures across the mod.
     */
    public static final String MODID = "toolsplusplus";
    public static final String NAME = "Tools++";
    public static final String VERSION = "1.0.0";
    public static final String MC_VERSION = "[1.12.2]";

    /**
     * 'Global' accessor for the creative tab so that its items can be easily loaded
     * into the creative tab.
     */
    public static final CreativeTabs PLUS_TAB = new PlusTab("plusTab");

    /**
     * 'Global' accessor for the debug logger so that there is easy access to the
     * instance across the mod namespace.
     */
    public static Logger logger;

    /**
     * Prior to the mod loading; Set the log instance that the mod will use so we
     * get access to the debug log.
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    /**
     * On the mod loading, display a message to flag that the mod has loaded as an
     * achor/frame of reference when looking through the debug log.
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info(ToolsPlusPlus.NAME + " loaded.");
    }
}
