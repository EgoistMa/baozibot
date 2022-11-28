package com.shiropure.handler;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface handler {
    /**
     * 处理消息的优先级，如果有多个处理者都对消息感兴趣，那么调用优先级最高的处理者处理
     */
    int priority() default 1;
}
