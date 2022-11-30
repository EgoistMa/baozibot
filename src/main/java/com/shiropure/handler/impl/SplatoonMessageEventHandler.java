package com.shiropure.handler.impl;

import com.shiropure.api.SplatoonApi;
import com.shiropure.config.RobotConfig;
import com.shiropure.exception.FileUploadException;
import com.shiropure.handler.handler;
import com.shiropure.proxy.Context;
import com.shiropure.utils.DateUtil;
import com.shiropure.utils.OfUtil;
import com.shiropure.utils.SplatoonUtil;
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
    public final String ZHENGE = "真格";
    public final String NEXTZHENGE = "下次真格";
    public final String TUDI = "涂地";
    public final String NEXTTUDI = "下次涂地";
    public final String XMODE = "x模式";
    public final String NEXTXMODE = "下次x模式";
    public final String ZUPAI = "组排";
    public final String NEXTZUPAI = "下次组排";
    public final int TWOHOUR = 7200;
    @Override
    public List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx) {
        try {
            String content = getPlantContent(event);
            //保存蛮颓计划的数据
            if (content.startsWith(ZHENGE)) {//当前蛮颓
                return getBankaraSchedules(SplatoonApi.SplatoonSchedules("bankaraSchedules"),event,0);
            }
            if (content.startsWith(NEXTZHENGE)) {//下场蛮颓
                return getBankaraSchedules(SplatoonApi.SplatoonSchedules("bankaraSchedules"),event,TWOHOUR);
            }
            if(content.startsWith(TUDI)){//当前涂地模式
                return getRegularSchedules(SplatoonApi.SplatoonSchedules("regularSchedules"),event,0);
            }
            if(content.startsWith(NEXTTUDI)){//下场涂地模式
                return getRegularSchedules(SplatoonApi.SplatoonSchedules("regularSchedules"),event,TWOHOUR);
            }
            if(content.startsWith(XMODE)){//下场涂地模式   X模式 和 涂地模式一样.偷懒 复用代码了
                return getRegularSchedules(SplatoonApi.SplatoonSchedules("xSchedules"),event,0);
            }
            if(content.startsWith(NEXTXMODE)){//下场涂地模式   X模式 和 涂地模式一样.偷懒 复用代码了
                return getRegularSchedules(SplatoonApi.SplatoonSchedules("xSchedules"),event,TWOHOUR);
            }
            if(content.startsWith(ZUPAI)){//下场涂地模式   组排模式 和 涂地模式一样.偷懒 复用代码了
                return getRegularSchedules(SplatoonApi.SplatoonSchedules("leagueSchedules"),event,0);
            }
            if(content.startsWith(NEXTZUPAI)){//下场涂地模式   组排模式 和 涂地模式一样.偷懒 复用代码了
                return getRegularSchedules(SplatoonApi.SplatoonSchedules("leagueSchedules"),event,TWOHOUR);
            }
            return buildMessageChainAsSingletonList(getQuoteReply(event), "不可能发生" + content);
        } catch (Exception e) {
            logError(event, e);
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
    }
    public List<MessageChain> getRegularSchedules(Map<String, Object> map,MessageEvent event, int offset) throws MalformedURLException, FileUploadException {
        List<MessageChain> ans = new ArrayList<>();
        MessageChainBuilder mc= new MessageChainBuilder();
        if (map == null) {
            return buildMessageChainAsSingletonList("未查询到相关数据", getQuoteReply(event));
        }
        OffsetDateTime now = OffsetDateTime.now( ZoneOffset.UTC );
        String DateRegion = DateUtil.getTimeRegion(now, offset);
        //mc.append("处理时间：UTC:"+ DateRegion + " 中国时区(UTC+8):"+ now.plusHours(8)+"\n");
        for (int i = 1; i < 3; i++)
        {
            StringBuilder sb = new StringBuilder();
            String eng[] = map.get(DateRegion+"matchNumber:"+i).toString().split("\\|");
            sb.append(SplatoonUtil.translateStage(eng[0]) +"\t"+ "<"+ SplatoonUtil.translateMode(eng[1])+">" +"\n");
            mc.append(sb.toString()).append(uploadImage(event,new URL(SplatoonUtil.getStageImg(SplatoonUtil.translateStage(eng[0])))));
        }
        ans.add(mc.build());

        return ans;
    }
    public List<MessageChain> getBankaraSchedules(Map<String, Object> map,MessageEvent event, int offset) throws MalformedURLException, FileUploadException {
        List<MessageChain> ans = new ArrayList<>();
        MessageChainBuilder mc= new MessageChainBuilder();
        if (map == null) {
            return buildMessageChainAsSingletonList("未查询到相关数据", getQuoteReply(event));
        }
        OffsetDateTime now = OffsetDateTime.now( ZoneOffset.UTC );
        String DateRegion = DateUtil.getTimeRegion(now, offset);
        //mc.append("处理时间：UTC:"+ DateRegion + " 中国时区(UTC+8):"+ now.plusHours(8)+"\n");
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
        return startWithKeywords(event, OfUtil.ofSet(ZHENGE, NEXTZHENGE,TUDI,NEXTTUDI,XMODE,NEXTXMODE,ZUPAI,NEXTZUPAI));
    }
}
