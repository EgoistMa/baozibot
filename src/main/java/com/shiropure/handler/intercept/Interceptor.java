package com.shiropure.handler.intercept;

import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.List;

public interface Interceptor {
    /**
     * 在事件到达 handler 之前拦截事件，如果返回真则将该事件拦截
     * @param event
     * @return 返回真拦截，返回假通过
     */
    boolean interceptBefore(MessageEvent event);


    /**
     * 在 handler 返回消息之后拦截事件及消息，在这里可以对消息进行一些处理，或者选择返回 null 以过滤消息
     * @param event 事件
     * @param mc 由 handler 返回的回复消息
     * @return 返沪经过处理后的消息，或者返回 null 以过滤此回复
     */
    List<MessageChain> interceptAfter(MessageEvent event, List<MessageChain> mc);
}

