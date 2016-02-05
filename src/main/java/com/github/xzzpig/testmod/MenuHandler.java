package com.github.xzzpig.testmod;

import java.io.IOException;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.client.event.GuiOpenEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;



public class MenuHandler {
	public static MenuHandler instance = new MenuHandler();
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void openMainMenu(GuiOpenEvent event) throws IOException {
		System.out.println(event.gui);
		if (event.gui instanceof GuiMainMenu) {
	    	System.out.println("loadGui");
			event.gui = new NewMenu();
	    	//event.gui.mc.displayGuiScreen(new TestMenu());
	    	System.out.println("GuiLoadFinish");
		}
	}
}
