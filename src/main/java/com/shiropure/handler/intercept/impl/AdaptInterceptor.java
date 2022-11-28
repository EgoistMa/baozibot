package com.shiropure.handler.intercept.impl;

import com.shiropure.handler.intercept.Interceptor;
import com.shiropure.utils.RobotUtil;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.List;

public class AdaptInterceptor extends RobotUtil implements Interceptor {
    @Override
    public boolean interceptBefore(MessageEvent event) {
        return false;
    }

    @Override
    public List<MessageChain> interceptAfter(MessageEvent event, List<MessageChain> mc) {
        return mc;
    }
}