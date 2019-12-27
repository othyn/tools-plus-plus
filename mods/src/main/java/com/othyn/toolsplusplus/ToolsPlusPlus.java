package com.othyn.toolsplusplus;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
	modid = ToolsPlusPlus.MODID,
	name = ToolsPlusPlus.NAME,
	version = ToolsPlusPlus.VERSION,
	acceptedMinecraftVersions = ToolsPlusPlus.MC_VERSION)

public class ToolsPlusPlus {

	public static final String MODID = "toolsplusplus";
	public static final String NAME = "Tools++";
	public static final String VERSION = "0.0.1";
	public static final String MC_VERSION = "[1.12.2]";

	public static final Logger LOGGER = LogManager.getLogger(ToolsPlusPlus.MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		LOGGER.info(ToolsPlusPlus.NAME + "says hi!");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
        
	}

}
