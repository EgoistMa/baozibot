package com.shiropure.handler.impl;

import com.shiropure.api.SplatoonApi;
import com.shiropure.exception.FileUploadException;
import com.shiropure.handler.handler;
import com.shiropure.proxy.Context;
import com.shiropure.utils.DateUtil;
import com.shiropure.utils.OfUtil;
import com.shiropure.utils.SplatoonUtil;
import io.ktor.http.Url;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

@handler
@SuppressWarnings("unchecked")
public class SplatoonMessageEventHandler extends GroupMessageEventHandler {
    public final String command1 = "真格";
    public final String command2 = "下次真格";

    public final int TWOHOUR = 7200;
    @Override
    public List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx) {
        try {
            String content = getPlantContent(event);
            //保存蛮颓计划的数据
            Map<String, Object> map = SplatoonApi.SplatoonSchedules("bankaraSchedules");
            if (content.startsWith(command1)) {//当前蛮颓
                return getBankaraSchedules(map,event,0);
            }
            if (content.startsWith(command2)) {//下场蛮颓
                return getBankaraSchedules(map,event,TWOHOUR);
            }
            return buildMessageChainAsSingletonList(getQuoteReply(event), "不可能发生" + content);
        } catch (Exception e) {
            logError(event, e);
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
    }
    public List<MessageChain> getBankaraSchedules(Map<String, Object> map,MessageEvent event, int offset) throws MalformedURLException, FileUploadException {
        List<MessageChain> ans = new ArrayList<>();
        MessageChainBuilder mc= new MessageChainBuilder();
        if (map == null) {
            return buildMessageChainAsSingletonList("未查询到相关数据", getQuoteReply(event));
        }
        OffsetDateTime now = OffsetDateTime.now( ZoneOffset.UTC );
        String DateRegion = DateUtil.getTimeRegion(now, offset);
        mc.append("处理时间：UTC:"+ DateRegion + " 中国时区(UTC+8):"+ now.plusHours(8));
        for (int i = 1; i < 5; i++)
        {
            StringBuilder sb = new StringBuilder();
            String eng[] = map.get(DateRegion+"matchNumber:"+i).toString().split("\\|");
            sb.append(SplatoonUtil.translateStage(eng[0]) +"\t\t"+"<"+ SplatoonUtil.translateRule(eng[1])+">" +"\t"+ "<"+ SplatoonUtil.translateMode(eng[2])+">" +"\n");
            mc.append(sb.toString()).append(uploadImage(event,new URL(SplatoonUtil.getStageImg(SplatoonUtil.translateStage(eng[0])))));
        }
        ans.add(mc.build());

        return ans;
    }

    @Override
    public boolean shouldHandle(MessageEvent event, Context ctx) {
        return startWithKeywords(event, OfUtil.ofSet(command1,command2));
    }
}
