package com.shiropure.handler.impl;

import com.shiropure.Model.Schedules.Schedules;
import com.shiropure.Model.Schedules.SplatoonSchedules;
import com.shiropure.Model.Schedules.Stage;
import com.shiropure.Model.Schedules.Weapon;
import com.shiropure.api.SplatoonSchedulesApi;
import com.shiropure.exception.FileUploadException;
import com.shiropure.handler.handler;
import com.shiropure.proxy.Context;
import com.shiropure.utils.DateUtil;
import com.shiropure.utils.SplatoonUtil;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@handler()
public class SplatoonMessageEventHandler extends GroupMessageEventHandler {
    public final String TUDI = "涂地";
    public final String ZHENGE = "真格";
    public final String XMODE = "x";
    public final String ZUPAI = "组排";
    public final String COOP = "打工";

    private Set<String> keywords;
    public SplatoonMessageEventHandler()
    {
        //aa
        keywords = new HashSet<>(16);
        keywords.add(stringFormateCommand(TUDI));
        keywords.add(stringFormateCommand(ZHENGE));
        keywords.add(stringFormateCommand(XMODE));
        keywords.add(stringFormateCommand(ZUPAI));
        keywords.add(stringFormateCommand(COOP));
    }
    @Override
    public List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx) {
        try {
            info("message handled by baoziBot");
            SplatoonSchedules schedules = SplatoonSchedulesApi.SplatoonSchedules();
            String content = getPlantContent(event);
            if(content.startsWith(stringFormateCommand(TUDI))) {
                info("涂地模式查询");
                return tuDI(event,schedules);
            }
            if(content.startsWith(stringFormateCommand(ZHENGE))) {
                info("真格模式查询");
                return zhenGe(event,schedules);
            }
            if(content.startsWith(stringFormateCommand(XMODE))) {
                info("X模式查询");
                return XMoShi(event,schedules);
            }
            if(content.startsWith(stringFormateCommand(ZUPAI))) {
                info("组排模式查询");
                return zuPai(event,schedules);
            }
            if(content.startsWith(stringFormateCommand(COOP))) {
                info("鲑鱼跑模式查询");
                return coop(event,schedules);
            }

        } catch (Exception e) {
            logError(event, e);
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
        return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：not implement error");
    }
    public List<MessageChain> coop(MessageEvent event,SplatoonSchedules schedules) throws IOException, FileUploadException {
        //子指令
        String content = getPlantContent(event);
        String subCommand = content.substring(formateCommand(COOP).length()).trim();
        //返回消息构造器
        List<MessageChain> ans = new ArrayList<>();
        //根据subcommand 获取处理
        if(subCommand.equals(""))
        {
            MessageChainBuilder mc= new MessageChainBuilder();
            StringBuilder sb = new StringBuilder();
            sb.append(DateUtil.getReadableChinaTime(schedules.coopGroupingSchedule[0].startTime)).append(" -> ").append(DateUtil.getReadableChinaTime(schedules.coopGroupingSchedule[0].endTime)).append("\n");
            mc.append(sb.toString());
            sb.delete(0,sb.length());
            Stage stage = schedules.coopGroupingSchedule[0].stages[0];
            sb.append(SplatoonUtil.translateStage(stage.stageName));
            mc.append(sb.toString()).append(uploadImage(event,new URL(stage.stageUrl)));
            sb.delete(0,sb.length());
            sb.append("武器：\n");
            for(Weapon weapon : schedules.coopGroupingSchedule[0].weapons)
            {
                sb.append(SplatoonUtil.translateWeapon(weapon.getWeaponName())).append("\t");
            }
            mc.append(sb.toString());
            sb.delete(0,sb.length());
            for(Weapon weapon : schedules.coopGroupingSchedule[0].weapons)
            {
                mc.append(uploadImage(event,new URL(weapon.getWeaponUrl())));
            }
            ans.add(mc.build());
        }else {
            for(Schedules schedules1 : schedules.coopGroupingSchedule)
            {
                MessageChainBuilder mc= new MessageChainBuilder();
                StringBuilder sb = new StringBuilder();
                sb.append(DateUtil.getReadableChinaTime(schedules1.startTime)).append(" -> ").append(DateUtil.getReadableChinaTime(schedules1.endTime)).append("\n");
                mc.append(sb.toString());
                sb.delete(0,sb.length());
                Stage stage = schedules1.stages[0];
                sb.append(SplatoonUtil.translateStage(stage.stageName));
                mc.append(sb.toString()).append(uploadImage(event,new URL(stage.stageUrl)));
                sb.delete(0,sb.length());
                sb.append("武器：\n");
                for(Weapon weapon : schedules1.weapons)
                {
                    sb.append(SplatoonUtil.translateWeapon(weapon.getWeaponName())).append("\t");
                }
                mc.append(sb.toString());
                sb.delete(0,sb.length());
                for(Weapon weapon : schedules1.weapons)
                {
                    mc.append(uploadImage(event,new URL(weapon.getWeaponUrl())));
                }
                ans.add(mc.build());
            }
        }
        return ans;
    }
    public List<MessageChain> zuPai(MessageEvent event,SplatoonSchedules schedules) throws IOException, FileUploadException {
        //子指令
        String content = getPlantContent(event);
        String subCommand = content.substring(formateCommand(ZUPAI).length()).trim();
        int offset = subCommand.length()-1;
        //返回消息构造器
        List<MessageChain> ans = new ArrayList<>();
        MessageChainBuilder mc= new MessageChainBuilder();
        StringBuilder sb = new StringBuilder();
        //根据subcommand 获取offset
        int index = Math.max(offset, 0);

        sb.append(DateUtil.getReadableChinaTime(schedules.leagueSchedules[index].startTime)).append(" -> ").append(DateUtil.getReadableChinaTime(schedules.leagueSchedules[index].endTime)).append("\n");
        mc.append(sb.toString());
        String rule = SplatoonUtil.translateRule(schedules.leagueSchedules[index].getRule());
        for(Stage stage : schedules.leagueSchedules[index].stages)
        {
            sb.delete(0,sb.length());
            sb.append(SplatoonUtil.translateStage(stage.stageName)).append("<").append(rule).append(">");
            mc.append(sb.toString()).append(uploadImage(event,new URL(stage.stageUrl)));
        }
        ans.add(mc.build());
        return ans;
    }
    public List<MessageChain> XMoShi(MessageEvent event,SplatoonSchedules schedules) throws IOException, FileUploadException {
        //子指令
        String content = getPlantContent(event);
        String subCommand = content.substring(formateCommand(XMODE).length()).trim();
        int offset = subCommand.length()-1;
        //返回消息构造器
        List<MessageChain> ans = new ArrayList<>();
        MessageChainBuilder mc= new MessageChainBuilder();
        StringBuilder sb = new StringBuilder();
        //根据subcommand 获取offset
        int index = Math.max(offset, 0);

        sb.append(DateUtil.getReadableChinaTime(schedules.xSchedules[index].startTime)).append(" -> ").append(DateUtil.getReadableChinaTime(schedules.xSchedules[index].endTime)).append("\n");
        mc.append(sb.toString());
        String rule = SplatoonUtil.translateRule(schedules.xSchedules[index].getRule());
        for(Stage stage : schedules.xSchedules[index].stages)
        {
            sb.delete(0,sb.length());
            sb.append(SplatoonUtil.translateStage(stage.stageName)).append("<").append(rule).append(">");
            mc.append(sb.toString()).append(uploadImage(event,new URL(stage.stageUrl)));
        }
        ans.add(mc.build());
        return ans;
    }
    public List<MessageChain> zhenGe(MessageEvent event,SplatoonSchedules schedules) throws IOException, FileUploadException {
        //子指令
        String content = getPlantContent(event);
        String subCommand = content.substring(formateCommand(ZHENGE).length()).trim();
        int offset = subCommand.length()-1;
        //返回消息构造器
        List<MessageChain> ans = new ArrayList<>();
        MessageChainBuilder mc= new MessageChainBuilder();
        StringBuilder sb = new StringBuilder();
        //根据subcommand 获取offset
        int index = 2*Math.max(offset, 0);

        sb.append(DateUtil.getReadableChinaTime(schedules.bankaraSchedules[index].startTime)+" -> "+DateUtil.getReadableChinaTime(schedules.bankaraSchedules[index].endTime) +"\n");
        mc.append(sb.toString());
        String rule = SplatoonUtil.translateRule(schedules.bankaraSchedules[index].getRule());
        String mode = SplatoonUtil.translateMode(schedules.bankaraSchedules[index].getMode());
        for(Stage stage : schedules.bankaraSchedules[index].stages)
        {
            sb.delete(0,sb.length());
            sb.append(SplatoonUtil.translateStage(stage.stageName)).append("<"+rule+">").append("<"+mode+">");
            mc.append(sb.toString()).append(uploadImage(event,new URL(stage.stageUrl)));
        }
        String rule2 = SplatoonUtil.translateRule(schedules.bankaraSchedules[index+1].getRule());
        String mode2 = SplatoonUtil.translateMode(schedules.bankaraSchedules[index+1].getMode());
        ans.add(mc.build());
        mc.clear();
        for(Stage stage : schedules.bankaraSchedules[index+1].stages)
        {
            sb.delete(0,sb.length());
            sb.append(SplatoonUtil.translateStage(stage.stageName)).append("<"+rule2+">").append("<"+mode2+">");
            mc.append(sb.toString()).append(uploadImage(event,new URL(stage.stageUrl)));
        }
        ans.add(mc.build());
        return ans;
    }
    public List<MessageChain> tuDI(MessageEvent event,SplatoonSchedules schedules) throws IOException, FileUploadException {
        //子指令
        String content = getPlantContent(event);
        String subCommand = content.substring(formateCommand(TUDI).length()).trim();
        int offset = subCommand.length()-1;
        //返回消息构造器
        List<MessageChain> ans = new ArrayList<>();
        MessageChainBuilder mc= new MessageChainBuilder();
        StringBuilder sb = new StringBuilder();
        //根据subcommand 获取offset
        int index = Math.max(offset, 0);

        sb.append(DateUtil.getReadableChinaTime(schedules.regularSchedules[index].startTime)+" -> "+DateUtil.getReadableChinaTime(schedules.regularSchedules[index].endTime) +"\n");
        mc.append(sb.toString());
        String rule = SplatoonUtil.translateRule(schedules.regularSchedules[index].getRule());
        for(Stage stage : schedules.regularSchedules[index].stages)
        {
            sb.delete(0,sb.length());
            sb.append(SplatoonUtil.translateStage(stage.stageName)).append("<"+rule+">");
            mc.append(sb.toString()).append(uploadImage(event,new URL(stage.stageUrl)));
        }
        ans.add(mc.build());
        return ans;
    }


    @Override
    public boolean shouldHandle(MessageEvent event, Context ctx) {
        return startWithKeywords(event,keywords);
    }
}
