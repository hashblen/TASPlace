package com.github.hashblen;

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

@Mod(modid = TASPlace.MODID, version = TASPlace.VERSION)
public class TASPlace
{
    public static final String MODID = "tasplace";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event){
        Minecraft minecraft = Minecraft.getMinecraft();
        if(event.phase!= TickEvent.Phase.START || !minecraft.isSingleplayer() || minecraft.currentScreen!=null) return;
        GameSettings gameSettings = minecraft.gameSettings;
        if(Mouse.isButtonDown(1)){
            //KeyBinding.setKeyBindState(gameSettings.keyBindUseItem.getKeyCode(), true);
            KeyBinding.onTick(gameSettings.keyBindUseItem.getKeyCode());
        }
    }
}
