package com.shiropure;

import com.shiropure.starter.BaoziBotStarter;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

public final class Baozibot extends JavaPlugin {
    public static final Baozibot INSTANCE = new Baozibot();

    private Baozibot() {
        super(new JvmPluginDescriptionBuilder("com.shiropure.baozibot", "0.1.0")
                .name("baozibot")
                .info("包子私人定制的小机器人")
                .author("egoist")
                .build());
    }

    @Override
    public void onEnable() {
        try{
            BaoziBotStarter.Start(this);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(500);
            getLogger().info("Baozi Bot Plugin loaded!");
        }
    }
}