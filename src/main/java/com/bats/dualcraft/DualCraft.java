package com.bats.dualcraft;

import com.bats.dualcraft.handler.GuiHandler;
import com.bats.dualcraft.handler.WorldEventHandler;
import com.bats.dualcraft.reference.Reference;
import com.bats.dualcraft.util.LogHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class DualCraft
{
    @Mod.Instance(Reference.MOD_ID)
    public static DualCraft instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        LogHelper.info("Beginning Init Phase");
        MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        LogHelper.info("Ending Init Phase");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
