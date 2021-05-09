package com.othyn.toolsplusplus.core.init;

import com.othyn.toolsplusplus.ToolsPlusPlus;
import com.othyn.toolsplusplus.common.items.AxePlusPlus;
import com.othyn.toolsplusplus.common.items.HoePlusPlus;
import com.othyn.toolsplusplus.common.items.IronBar;
import com.othyn.toolsplusplus.common.items.PickaxePlusPlus;
import com.othyn.toolsplusplus.common.items.ShovelPlusPlus;
import com.othyn.toolsplusplus.core.enums.PlusPlusMaterial;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = ToolsPlusPlus.MOD_ID, bus = Bus.MOD)
public class RegisterItems {
	
	public static final IronBar ironBar = new IronBar();
	
	public static final AxePlusPlus axePlusPlus = new AxePlusPlus(new PlusPlusMaterial());
	public static final HoePlusPlus hoePlusPlus = new HoePlusPlus(new PlusPlusMaterial());
	public static final PickaxePlusPlus pickaxePlusPlus = new PickaxePlusPlus(new PlusPlusMaterial());
	public static final ShovelPlusPlus shovelPlusPlus = new ShovelPlusPlus(new PlusPlusMaterial());

    @SubscribeEvent
    public static void register(Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        
        ironBar.setRegistryName(ToolsPlusPlus.MOD_ID, "iron_bar");
        
        registry.registerAll(ironBar);
        
        axePlusPlus.setRegistryName(ToolsPlusPlus.MOD_ID, "axe_plus_plus");
        hoePlusPlus.setRegistryName(ToolsPlusPlus.MOD_ID, "hoe_plus_plus");
        pickaxePlusPlus.setRegistryName(ToolsPlusPlus.MOD_ID, "pickaxe_plus_plus");
        shovelPlusPlus.setRegistryName(ToolsPlusPlus.MOD_ID, "shovel_plus_plus");
        
        registry.registerAll(axePlusPlus, hoePlusPlus, pickaxePlusPlus, shovelPlusPlus);
    }
}
