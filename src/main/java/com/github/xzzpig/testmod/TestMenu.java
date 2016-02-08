package com.github.xzzpig.testmod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.multiplayer.ServerData;

import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import com.github.xzzpig.pigapi.TData;
import com.github.xzzpig.pigapi.tcp.Client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TestMenu extends GuiScreen implements GuiYesNoCallback
{
	private static final Logger logger = LogManager.getLogger();
	
    private GuiTextField text_passowrd;
    private GuiButton button_login;
    private static final String __OBFID = "CL_00000695";
    public static String message,result = "";
    
	
    public TestMenu()
    {
    	super();
    	System.out.println("new Menu");
    }
    
    
    public void updateScreen()
    {
        this.text_passowrd.updateCursorCounter();
//        this.field_146308_f.updateCursorCounter();
    }
    
    @Override
    public boolean doesGuiPauseGame() {
    	return false;
    }
    
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
    	System.out.println("initGui()Start");
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
//        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 18, I18n.format("addServer.add", new Object[0])));
//        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 18, I18n.format("gui.cancel", new Object[0])));
        this.buttonList.add(this.button_login = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 72,"登录并进入服务器" ));
        this.text_passowrd = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, 66, 200, 20);
        this.text_passowrd.setFocused(true);
        //this.field_146309_g.setText(this.field_146311_h.serverName);
//        this.field_146308_f = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, 106, 200, 20);
//        this.field_146308_f.setMaxStringLength(128);
        //this.field_146308_f.setText(this.field_146311_h.serverIP);
        ((GuiButton)this.buttonList.get(0)).enabled = this.text_passowrd.getText().length() > 0;
    	System.out.println("initGui()Finish");
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton button)
    {
        if (!button.enabled)
        	return;
        if(button.id == 0)
        	onLoginButtonClick();
    }
    
    private void onLoginButtonClick() {
    	message = "开始连接服务器";
    	try {
			new Client("xzz2.xzzpig.club",10727);
		} catch (Exception e) {
			message = "服务器连接失败";
			return;
		}
    	Client client = Client.client;
    	client.sendData(new TData().setString("password",button_login.displayString));
		message = "登录成功";
    }


	/**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char c, int id)
    {
    	System.out.println(c+""+id);
        this.text_passowrd.textboxKeyTyped(c, id);
//        this.field_146308_f.textboxKeyTyped(p_73869_1_, p_73869_2_);
        if (id == 15)
        {
            this.text_passowrd.setFocused(!this.text_passowrd.isFocused());
//            this.field_146308_f.setFocused(!this.field_146308_f.isFocused());
        }

        if (id == 28 || id == 156)
        {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }

        ((GuiButton)this.buttonList.get(0)).enabled = this.text_passowrd.getText().length() > 0;
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
//        this.field_146308_f.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        this.text_passowrd.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.drawBackground(10);
        this.drawCenteredString(this.fontRendererObj, "登录界面", this.width / 2, 17, 16777215);
        this.drawString(this.fontRendererObj, "密码:", this.width / 2 - 100, 53, 10526880);
        this.text_passowrd.drawTextBox();
        if(message != null)
        	this.drawString(this.fontRendererObj, "信息:"+message, this.width / 2 - 100, 100, 10526880);
        if(result.equalsIgnoreCase("success"))
        	FMLClientHandler.instance().connectToServer(this, new ServerData("TestServer", "xzz2.xzzpig.club;25565"));
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}