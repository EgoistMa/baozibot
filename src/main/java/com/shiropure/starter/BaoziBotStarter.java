package com.shiropure.starter;

import com.shiropure.config.RobotConfig;
import com.shiropure.cron.RobotCronJob;
import com.shiropure.proxy.MessageHandlerProxy;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;

public class BaoziBotStarter {
    private static MessageHandlerProxy messageHandler;
    public static void Start(JavaPlugin plugin) throws Exception {
        RobotConfig.logger = plugin.getLogger();
        //消息代理
        messageHandler = new MessageHandlerProxy();
        // 订阅消息事件
        EventChannel<Event> instance =  GlobalEventChannel.INSTANCE.parentScope(com.shiropure.Baozibot.INSTANCE);;
        instance.subscribeAlways(GroupMessageEvent.class, event -> {
            if (messageHandler.shouldHandle(event, null)) {
                messageHandler.handleMessageEvent(event, null);
            }
        });
    }
}
