package com.shiropure.proxy;

import com.shiropure.config.RobotConfig;
import com.shiropure.handler.MessageEventHandler;
import com.shiropure.handler.handler;
import com.shiropure.handler.intercept.Interceptor;
import com.shiropure.handler.intercept.intercept;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MessageHandlerProxy implements MessageEventHandler {
    public final String packageName = "com.shiropure";
    private final List<MessageEventHandler> handlers = new ArrayList<>();
    private final List<Interceptor> interceptors = new ArrayList<>();
    public MessageHandlerProxy() throws Exception {
        Reflections reflections = new Reflections("com.shiropure");
        Set<Class<?>> handlerCs = reflections.getTypesAnnotatedWith(handler.class);
        Set<Class<?>> inspectCs = reflections.getTypesAnnotatedWith(intercept.class);


        for (Class<?> c : handlerCs) {
            if (Modifier.isAbstract(c.getModifiers())) {
                continue;
            }
            try {
                handlers.add((MessageEventHandler) c.getConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Class<?> c : inspectCs) {
            try {
                interceptors.add((Interceptor) c.getConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
                throw  e;
            }
        }

        handlers.sort((h1, h2) -> {
            handler a1 = h1.getClass().getAnnotation(handler.class);
            handler a2 = h2.getClass().getAnnotation(handler.class);
            // 按照高 ->低排序
            return a2.priority() - a1.priority();
        });

        interceptors.sort((i1, i2) -> {
            intercept a1 = i1.getClass().getAnnotation(intercept.class);
            intercept a2 = i2.getClass().getAnnotation(intercept.class);
            // 按照高 ->低排序
            return a2.order() - a1.order();
        });
        RobotConfig.logger.info("HRobot handlers and filters loading finished, total " + handlers.size() + " handlers, " + interceptors.size() + " inspectors.");
    }
    @Override
    public List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx) {
        if (ctx == null) {
            ctx = new Context(handlers, interceptors);
        }
        ctx. execute(event);
        return null;
    }

    @Override
    public boolean shouldHandle(MessageEvent event, Context ctx) {
        for (Interceptor filter : interceptors) {
            if (filter.interceptBefore(event)) {
                // 如果事件被指明过滤，则不回复
                return false;
            }
        }
        return true;
    }
}
