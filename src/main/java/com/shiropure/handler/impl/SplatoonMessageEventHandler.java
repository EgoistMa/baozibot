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
    public final String NEXTZHENGE2 = "下场真格";
    public final String TUDI = "涂地";
    public final String NEXTTUDI = "下次涂地";
    public final String NEXTTUDI2 = "下场涂地";
    public final String XMODE = "x";
    public final String XMODE2 = "X";
    public final String NEXTXMODE = "下次x";
    public final String NEXTXMODE2 = "下场x";
    public final String NEXTXMODE3 = "下次x";
    public final String NEXTXMODE4 = "下场x";
    public final String ZUPAI = "组排";
    public final String NEXTZUPAI = "下次组排";
    public final String NEXTZUPAI2 = "下场组排";
    public final String HELP = "help";
    public final String HELP2 = "HELP";
    public final String HELP3 = "Help";
    public final int TWOHOUR = 7200;

    private Set<String> keywords;
    public SplatoonMessageEventHandler()
    {
        keywords = new HashSet<>(16);
        keywords.add(formateCommand(ZHENGE));
        keywords.add(formateCommand(NEXTZHENGE));
        keywords.add(formateCommand(NEXTZHENGE2));
        keywords.add(formateCommand(TUDI));
        keywords.add(formateCommand(NEXTTUDI));
        keywords.add(formateCommand(NEXTTUDI2));
        keywords.add(formateCommand(XMODE));
        keywords.add(formateCommand(XMODE2));
        keywords.add(formateCommand(NEXTXMODE));
        keywords.add(formateCommand(NEXTXMODE2));
        keywords.add(formateCommand(NEXTXMODE3));
        keywords.add(formateCommand(NEXTXMODE4));
        keywords.add(formateCommand(ZUPAI));
        keywords.add(formateCommand(NEXTZUPAI));
        keywords.add(formateCommand(NEXTZUPAI2));
        keywords.add(formateCommand(HELP));
        keywords.add(formateCommand(HELP2));
        keywords.add(formateCommand(HELP3));
    }
    @Override
    public List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx) {
        try {
            String content = getPlantContent(event);
            //保存蛮颓计划的数据
            if (content.startsWith(ZHENGE)) {//当前蛮颓
                return getBankaraSchedules(SplatoonApi.SplatoonSchedules("bankaraSchedules"),event,0);
            }
            if (content.startsWith(NEXTZHENGE)||content.startsWith(NEXTZHENGE2)) {//下场蛮颓
                return getBankaraSchedules(SplatoonApi.SplatoonSchedules("bankaraSchedules"),event,TWOHOUR);
            }
            if(content.startsWith(TUDI)){//当前涂地模式
                return getRegularSchedules(SplatoonApi.SplatoonSchedules("regularSchedules"),event,0);
            }
            if(content.startsWith(NEXTTUDI)||content.startsWith(NEXTTUDI2)){//下场涂地模式
                return getRegularSchedules(SplatoonApi.SplatoonSchedules("regularSchedules"),event,TWOHOUR);
            }
            if(content.startsWith(XMODE)||content.startsWith(XMODE2)){//下场涂地模式   X模式 和 涂地模式一样.偷懒 复用代码了
                return getRegularSchedules(SplatoonApi.SplatoonSchedules("xSchedules"),event,0);
            }
            if(content.startsWith(NEXTXMODE)||content.startsWith(NEXTXMODE2)||content.startsWith(NEXTXMODE3)||content.startsWith(NEXTXMODE4)){//下场涂地模式   X模式 和 涂地模式一样.偷懒 复用代码了
                return getRegularSchedules(SplatoonApi.SplatoonSchedules("xSchedules"),event,TWOHOUR);
            }
            if(content.startsWith(ZUPAI)){//下场涂地模式   组排模式 和 涂地模式一样.偷懒 复用代码了
                return getRegularSchedules(SplatoonApi.SplatoonSchedules("leagueSchedules"),event,0);
            }
            if(content.startsWith(NEXTZUPAI)||content.startsWith(NEXTZUPAI2)){//下场涂地模式   组排模式 和 涂地模式一样.偷懒 复用代码了
                return getRegularSchedules(SplatoonApi.SplatoonSchedules("leagueSchedules"),event,TWOHOUR);
            }
            if(content.startsWith(HELP)||content.startsWith(HELP2)||content.startsWith(HELP3)){
                return getHelp();
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
    public  List<MessageChain> getHelp(){
        List<MessageChain> ans = new ArrayList<>();
        MessageChainBuilder mc= new MessageChainBuilder();
        mc.append("馒头bot使用指南\n" +
                "查询当前涂地:            ，涂地\n" +
                "查询下场涂地:            ，下场(次)涂地\n" +
                "查询当前真格挑战和开放:    ，真格\n" +
                "查询下场真格挑战和开放:    ，下场(次)真格\n" +
                "查询当前组排:            ，组排\n" +
                "查询下次组排             ，下场(次)组排\n" +
                "查询当前x赛:             ，x\n" +
                "查询下场x赛:             ，下场(次)x\n" +
                "随机抽取一个武器          ，随机武器\n\n\n" +
                "还在开发中项目......\n" +
                "鲑鱼跑查询\n" +
                "保存sw好友码\n\n" +
                "有什么需要的功能可以提，能力有限尽量qwq");
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
        return startWithKeywords(event,keywords);
    }
}
