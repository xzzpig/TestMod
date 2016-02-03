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
	private ServerData field_146811_z;
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void openMainMenu(GuiOpenEvent event) throws IOException {
		if (event.gui instanceof GuiMainMenu) {
			event.gui = new Menu();
		}
	}
}
