package com.shiropure.utils;

import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.*;

import java.util.List;

public class RobotUtil {
    public static List<MessageChain> buildMessageChainAsList(MessageChain... m) {
        return OfUtil.ofList(m);
    }
    public static MessageChain buildMessageChain(String... m) {
        MessageChainBuilder builder = new MessageChainBuilder();
        for (String s : m) {
            builder.append(s);
        }
        return builder.build();
    }
    public static MessageChain buildMessageChain(Object... m) {
        MessageChainBuilder builder = new MessageChainBuilder();
        for (Object s : m) {
            if (s == null) {
                continue;
            }
            if (s instanceof String) {
                s = new PlainText((CharSequence) s);
            }
            if (s instanceof StringBuilder) {
                s = new PlainText((CharSequence) s.toString());
            }
            if (s instanceof SingleMessage) {
                builder.append((SingleMessage) s);
            }
        }
        return builder.build();
    }
    public static List<MessageChain> buildMessageChainAsSingletonList(Object... m) {
        return OfUtil.ofList(buildMessageChain(m));
    }
    public static String getContent(MessageEvent event) {
        if (event == null) {
            return null;
        }
        return getContent(event.getMessage());
    }
    public static String getContent(MessageChain chain) {
        if (chain == null) {
            return null;
        }
        return chain.serializeToMiraiCode();
    }
    public static String getOnlyPlainContent(MessageEvent event) {
        if (event == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (SingleMessage singleMessage : event.getMessage()) {
            if (singleMessage instanceof PlainText) {
                sb.append(singleMessage);
            }
        }
        return sb.toString().trim();
    }
    public static String getSenderId(MessageEvent event) {
        return String.valueOf(event.getSender().getId());
    }

    public static long getSenderId2(MessageEvent event) {
        return event.getSender().getId();
    }

    /**
     * 引用回复一条消息
     *
     * @param event
     * @param msg
     * @return
     */
    public static MessageChain quoteReply(MessageEvent event, MessageChain msg) {
        return buildMessageChain(getQuoteReply(event), msg);
    }
    /**
     * 设置引用回复，如果失败，则返回 null<br/>
     * 如果想回复某消息，你可以这样做：chainBuilder.append(getQuoteReply(e))<br/>或者调用父类方法：buildMessageChain(getQuoteReply(e), msg) 以构造一条消息链<br/>或者使用 getQuoteReply 方法回复一条简单文本信息
     *
     * @param event
     * @return MessageSource
     * @see #buildMessageChain(Object...)
     * @see #quoteReply(MessageEvent, MessageChain)
     */
    public static QuoteReply getQuoteReply(MessageEvent event) {
        return new QuoteReply(event.getMessage());
    }
}
