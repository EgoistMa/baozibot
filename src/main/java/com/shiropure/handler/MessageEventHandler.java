package com.shiropure.handler;

import com.shiropure.proxy.Context;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.List;

public interface MessageEventHandler {
    /**
     * 处理一个新的消息事件，返回要回复的消息，可以是多条，也可以为 null（代表不回复）
     *
     * @param event
     * @param ctx
     */
    List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx);


    /**
     * 是否应该处理事件，子类应该扩展它
     *
     * @param event
     * @return <strong>如果需要处理，则返回 true；如果不需要处理，则返回 false</strong>
     */
    boolean shouldHandle(MessageEvent event, Context ctx);
}
