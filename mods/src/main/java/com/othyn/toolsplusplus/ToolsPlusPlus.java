package com.othyn.toolsplusplus;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.othyn.toolsplusplus.core.init.ItemInit;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ToolsPlusPlus.MOD_ID)
public class ToolsPlusPlus
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    
    public static final String MOD_ID = "toolsplusplus";

    public ToolsPlusPlus() {
    	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
        // Register the setup method for modloading
        eventBus.addListener(this::setup);
        
        // Register the defined items
        ItemInit.ITEMS.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	
    }
}
