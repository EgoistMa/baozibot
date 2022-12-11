package com.shiropure.handler.impl;

import com.shiropure.Model.Gear.Shop;
import com.shiropure.Model.Schedules.SplatoonSchedules;
import com.shiropure.api.SplatoonGearApi;
import com.shiropure.api.SplatoonSchedulesApi;
import com.shiropure.proxy.Context;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SplatoonGearMessageEventHandler extends GroupMessageEventHandler{
    public final String Gear = "商店";

    private Set<String> keywords;
    public SplatoonGearMessageEventHandler()
    {
        //aa
        keywords = new HashSet<>(16);
        keywords.add(stringFormateCommand(Gear));
    }
    public List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx) {
        try {
            logger.info("message handled by baoziBot");
            Shop shop = SplatoonGearApi.GearShop();
            String content = getPlantContent(event);
            if (content.startsWith(formateCommand(Gear))) {
                logger.info("商店查询");
                return gears(event, shop);
            }
        } catch (Exception e) {
            logError(event, e);
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
        return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：not implement error");
    }
    public List<MessageChain> gears(MessageEvent event,Shop shop)
    {
        return null;//todo
    }

    public boolean shouldHandle(MessageEvent event, Context ctx) {
        return startWithKeywords(event,keywords);
    }

    @Override
    public String toString() {
        return "SplatoonGearMessageEventHandler{" +
                "Gear='" + Gear + '\'' +
                ", keywords=" + keywords +
                '}'+
                '\n';
    }
}
