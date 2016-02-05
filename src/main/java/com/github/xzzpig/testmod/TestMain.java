package com.github.xzzpig.testmod;

import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.Display;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = TestMain.MODID, version = TestMain.VERSION,name = TestMain.NAME)
public class TestMain{
    public static final String MODID = "TestMod";
    public static final String VERSION = "1.0";
    public static final String NAME = "TestMod";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("TestModLaunchSuccess");
    }
    
    @Mod.EventHandler
	@SideOnly(Side.CLIENT)
	public void preInit(FMLPreInitializationEvent e)
	{
		Display.setTitle("Test");
		MinecraftForge.EVENT_BUS.register(MenuHandler.instance);
	}
}
