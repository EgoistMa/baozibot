package com.shiropure.handler.impl;

import com.shiropure.api.SplatoonApi;
import com.shiropure.handler.handler;
import com.shiropure.proxy.Context;
import com.shiropure.utils.OfUtil;
import com.shiropure.utils.StringUtil;
import kotlinx.serialization.json.Json;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.*;

@handler
@SuppressWarnings("unchecked")
public class SplatoonMessageEventHandler extends GroupMessageEventHandler {
    public final String command1 = "喷喷";
    public final String command2 = "喷2";
    @Override
    public List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx) {
        try {
            //todo 优化这里
            String content = getPlantContent(event);
            StringBuilder sb = new StringBuilder();
            if (content.startsWith(command1)) {
                Map<String, Object> map = SplatoonApi.SplatoonSchedules();
                if (map == null) {
                    return buildMessageChainAsSingletonList("未查询到相关数据", getQuoteReply(event));
                }
                map.forEach((key, value) -> {
                    sb.append(key + " + " + value + "\n");
                });
                return buildMessageChainAsSingletonList(sb.toString());
            }
            if (content.startsWith(command2)) {
                return buildMessageChainAsSingletonList("命令 喷2 执行");
            }
            return buildMessageChainAsSingletonList(getQuoteReply(event), "不可能发生" + content);
        } catch (Exception e) {
            logError(event, e);
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
    }


    @Override
    public boolean shouldHandle(MessageEvent event, Context ctx) {
        return startWithKeywords(event, OfUtil.ofSet(command1,command2));
    }
}
