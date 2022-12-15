package com.shiropure.handler.impl;

import com.shiropure.Model.Gear.Shop;
import com.shiropure.Model.Schedules.Schedules;
import com.shiropure.Model.Schedules.SplatoonSchedules;
import com.shiropure.Model.Schedules.Weapon;
import com.shiropure.api.SplatoonGearApi;
import com.shiropure.api.SplatoonSchedulesApi;
import com.shiropure.exception.FileUploadException;
import com.shiropure.handler.handler;
import com.shiropure.proxy.Context;
import com.shiropure.utils.ImageUtil;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.shiropure.utils.DateUtil.HHformater;
import static com.shiropure.utils.SplatoonUtil.translateStage;
import static com.shiropure.utils.SplatoonUtil.translateWeapon;

@handler()
public class SplatoonGuiMessageEventHandler extends GroupMessageEventHandler {
    public final String TUDI = "涂地";
    public final String ZHENGE = "真格";
    public final String XMODE = "x";
    public final String ZUPAI = "组排";
    public final String COOP = "打工";
    public final String SHOP = "商店";
    private Set<String> keywords;
    public SplatoonGuiMessageEventHandler() {
        keywords = new HashSet<>(16);
        keywords.add(formateCommand(TUDI));
        keywords.add(formateCommand(ZHENGE));
        keywords.add(formateCommand(XMODE));
        keywords.add(formateCommand(ZUPAI));
        keywords.add(formateCommand(COOP));
        keywords.add(formateCommand(SHOP));
    }

    @Override
    public List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx) {
        try {
            info("message handled by baoziBot");
            SplatoonSchedules schedules = SplatoonSchedulesApi.SplatoonSchedules();
            String content = getPlantContent(event);
            if (content.startsWith(formateCommand(TUDI))) {
                info("涂地模式查询");
                return tuDI(event, schedules);
            }
            if (content.startsWith(formateCommand(ZHENGE))) {
                info("真格模式查询");
                return zhenGe(event, schedules);
            }
            if (content.startsWith(formateCommand(XMODE))) {
                info("X模式查询");
                return X(event, schedules);
            }
            if (content.startsWith(formateCommand(ZUPAI))) {
                info("组排模式查询");
                return zuPai(event, schedules);
            }
            if (content.startsWith(formateCommand(COOP))) {
                info("鲑鱼跑模式查询");
                return coop(event, schedules);
            }
            if (content.startsWith(formateCommand(SHOP))) {
                info("商店查询");
                return shop(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
        return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：not implement error");
    }

    private List<MessageChain> shop(MessageEvent event) throws IOException {
        Shop shop = SplatoonGearApi.GearShop();
        String imagePath = ImageUtil.shopImg(shop);
        info(imagePath);
        return sendImage(imagePath,event);
    }

    private List<MessageChain> zuPai(MessageEvent event, SplatoonSchedules schedules) throws IOException {
        String content = getPlantContent(event);
        String subcommand = content.substring(formateCommand(ZUPAI).length()).trim();
        int index = getIndex(subcommand);
        Schedules[] leagueSchedules = schedules.leagueSchedules;
        String time = "";
        time += HHformater(leagueSchedules[index].startTime.plusHours(8).getHour()) +":00-";
        time += HHformater(leagueSchedules[index].endTime.plusHours(8).getHour()) +":00";
        String name1 = translateStage(leagueSchedules[index].getStages()[0].stageName);
        String name2 =  translateStage(leagueSchedules[index].getStages()[1].stageName);
        String url1 =  translateStage(leagueSchedules[index].getStages()[0].stageUrl);
        String url2 =  translateStage(leagueSchedules[index].getStages()[1].stageUrl);
        String rule = leagueSchedules[index].getRule();
        HashMap<String,String> map = new HashMap<>();
        map.put("time",time);
        map.put("name1",name1);
        map.put("name2",name2);
        map.put("url1",url1);
        map.put("url2",url2);
        map.put("rule1",rule);
        String imagePath = ImageUtil.mapImgGenerator(map,"league");
        //上传生成文件，并发送
        return sendImage(imagePath,event);
    }

    private List<MessageChain> coop(MessageEvent event, SplatoonSchedules schedules) throws IOException, FileUploadException {
        String content = getPlantContent(event);
        String subcommand = content.substring(formateCommand(COOP).length()).trim();
        int index = getIndex(subcommand);
        Schedules[] coopSchedules = schedules.coopGroupingSchedule;
        String startTime = "";
        startTime += coopSchedules[index].startTime.getMonthValue() +"/" +coopSchedules[index].startTime.getDayOfMonth()+" ";
        startTime += HHformater(coopSchedules[index].startTime.plusHours(8).getHour()) +":00";
        String endTime = "";
        endTime += coopSchedules[index].endTime.getMonthValue() +"/" +coopSchedules[index].endTime.getDayOfMonth()+" ";
        endTime += HHformater(coopSchedules[index].endTime.plusHours(8).getHour()) +":00";
        String mapName = translateStage(coopSchedules[index].getStages()[0].stageName);
        String mapUrl = coopSchedules[index].getStages()[0].stageUrl;
        /* 临时文字版
        MessageChainBuilder mc= new MessageChainBuilder();
        StringBuilder sb = new StringBuilder();
        List<MessageChain> ans = new ArrayList<>();
        sb.append(time).append("\n");
        sb.append("地图是:").append(mapName).append("\n");
        mc.append(sb.toString());
        sb.delete(0,sb.length());
        mc.append(uploadImage(event,new URL(mapUrl)));
        sb.append("发放武器：");
        for(String weapon:weapons)
        {
            sb.append(weapon).append(',');
        }
        sb.append("\n");
        mc.append(sb);
        ans.add(mc.build());
        return ans;
        */
        HashMap<String,String> map = new HashMap<>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("name",mapName);
        map.put("url",mapUrl);
        map.put("weapon1",coopSchedules[index].weapons[0].getWeaponUrl());
        map.put("weapon2",coopSchedules[index].weapons[1].getWeaponUrl());
        map.put("weapon3",coopSchedules[index].weapons[2].getWeaponUrl());
        map.put("weapon4",coopSchedules[index].weapons[3].getWeaponUrl());
        String imagePath = ImageUtil.salmonRun(map);
        return sendImage(imagePath,event);
    }

    private List<MessageChain> X(MessageEvent event, SplatoonSchedules schedules) throws IOException {
        String content = getPlantContent(event);
        String subcommand = content.substring(formateCommand(XMODE).length()).trim();
        int index = getIndex(subcommand);
        Schedules[] xSchedules = schedules.xSchedules;
        String time = "";
        time += HHformater(xSchedules[index].startTime.plusHours(8).getHour()) +":00-";
        time += HHformater(xSchedules[index].endTime.plusHours(8).getHour()) +":00";
        String name1 = translateStage(xSchedules[index].getStages()[0].stageName);
        String name2 =  translateStage(xSchedules[index].getStages()[1].stageName);
        String url1 =  translateStage(xSchedules[index].getStages()[0].stageUrl);
        String url2 =  translateStage(xSchedules[index].getStages()[1].stageUrl);
        String rule = xSchedules[index].getRule();
        HashMap<String,String> map = new HashMap<>();
        map.put("time",time);
        map.put("name1",name1);
        map.put("name2",name2);
        map.put("url1",url1);
        map.put("url2",url2);
        map.put("rule1",rule);
        String imagePath = ImageUtil.mapImgGenerator(map,"x");
        //上传生成文件，并发送
        return sendImage(imagePath,event);
    }

    private List<MessageChain> zhenGe(MessageEvent event, SplatoonSchedules schedules) throws IOException {
        String content = getPlantContent(event);
        String subcommand = content.substring(formateCommand(TUDI).length()).trim();
        int index = getIndex(subcommand);
        Schedules[] bankaraSchedules = schedules.bankaraSchedules;
        String time = "";
        time += HHformater(bankaraSchedules[index*2].startTime.plusHours(8).getHour()) +":00-";
        time += HHformater(bankaraSchedules[index*2].endTime.plusHours(8).getHour()) +":00";
        String name1 = translateStage(bankaraSchedules[index*2].getStages()[0].stageName);
        String name2 =  translateStage(bankaraSchedules[index*2].getStages()[1].stageName);
        String name3 = translateStage(bankaraSchedules[index*2 + 1].getStages()[0].stageName);
        String name4 =  translateStage(bankaraSchedules[index*2 + 1].getStages()[1].stageName);
        String url1 =  translateStage(bankaraSchedules[index*2].getStages()[0].stageUrl);
        String url2 =  translateStage(bankaraSchedules[index*2].getStages()[1].stageUrl);
        String url3 =  translateStage(bankaraSchedules[index*2 + 1].getStages()[0].stageUrl);
        String url4 =  translateStage(bankaraSchedules[index*2 + 1].getStages()[1].stageUrl);
        String rule1 = bankaraSchedules[index*2].getRule();
        String rule2 = bankaraSchedules[index*2 + 1].getRule();
        HashMap<String,String> map = new HashMap<>();
        map.put("time",time);
        map.put("name1",name1);
        map.put("name2",name2);
        map.put("name3",name3);
        map.put("name4",name4);
        map.put("url1",url1);
        map.put("url2",url2);
        map.put("url3",url3);
        map.put("url4",url4);
        map.put("rule1",rule1);
        map.put("rule2",rule2);
        String imagePath = ImageUtil.mapImgGenerator(map,"bankara");
        info("包子功能完成");
        //上传生成文件，并发送
        return sendImage(imagePath,event);
    }

    private List<MessageChain> tuDI(MessageEvent event, SplatoonSchedules schedules) throws IOException {
        String content = getPlantContent(event);
        String subcommand = content.substring(formateCommand(TUDI).length()).trim();
        int index = getIndex(subcommand);
        //获取当前场次信息
        Schedules[] tudiSchedules = schedules.regularSchedules;
        String time = "";
        time += HHformater(tudiSchedules[index].startTime.plusHours(8).getHour()) +":00-";
        time += HHformater(tudiSchedules[index].endTime.plusHours(8).getHour()) +":00";
        String name1 = translateStage(tudiSchedules[index].getStages()[0].stageName);
        String name2 =  translateStage(tudiSchedules[index].getStages()[1].stageName);
        String url1 =  translateStage(tudiSchedules[index].getStages()[0].stageUrl);
        String url2 =  translateStage(tudiSchedules[index].getStages()[1].stageUrl);
        //生成map体 供包子写的 mapImgGenerator 函数
        HashMap<String,String> map = new HashMap<>();
        map.put("time",time);
        map.put("name1",name1);
        map.put("name2",name2);
        map.put("url1",url1);
        map.put("url2",url2);
        String imagePath = ImageUtil.mapImgGenerator(map,"regular");
        info("包子功能完成");
        //上传生成文件，并发送
        return sendImage(imagePath,event);
    }
    public int getIndex(String command)
    {
        return Math.max(command.length()-1, 0);
    }
    public boolean shouldHandle(MessageEvent event, Context ctx) {
        return startWithKeywords(event,keywords);
    }
}