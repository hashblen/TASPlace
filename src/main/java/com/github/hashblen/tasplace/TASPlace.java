package com.github.hashblen.tasplace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;


@Mod(modid = TASPlace.MODID, version = TASPlace.VERSION, acceptedMinecraftVersions = "[1.8,1.12.2]")
public class TASPlace
{
    public static final String MODID = "tasplace";
    public static final String VERSION = "1.1";
    public static KeyBinding toggle = new KeyBinding("Toggle Fastplace", Keyboard.KEY_G, "TASPlace");
    public static boolean isToggled = true;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientRegistry.registerKeyBinding(toggle);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event){
        Minecraft minecraft = Minecraft.getMinecraft();
        if(toggle.isPressed())
            isToggled = !isToggled;

        if(event.phase!= TickEvent.Phase.START
                || !minecraft.isSingleplayer()
                || minecraft.currentScreen!=null
                || !isToggled) return;

        GameSettings gameSettings = minecraft.gameSettings;
        int keyCode = gameSettings.keyBindUseItem.getKeyCode();
        if((keyCode>=-100 && keyCode<=-85)){ //mouse button 0 (left click) to button 15 (max)
            //KeyBinding.setKeyBindState(gameSettings.keyBindUseItem.getKeyCode(), true);
            if(Mouse.isButtonDown(keyCode+100))
                KeyBinding.onTick(keyCode);
        }
        else if(Keyboard.isKeyDown(keyCode)){
            //System.out.println(keyCode);
            KeyBinding.onTick(keyCode);
        }

        //System.out.println(Keyboard.getKeyName(keyCode));
    }
}
