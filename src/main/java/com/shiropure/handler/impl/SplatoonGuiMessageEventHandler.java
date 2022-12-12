package com.shiropure.handler.impl;

import com.shiropure.Model.Schedules.Schedules;
import com.shiropure.Model.Schedules.SplatoonSchedules;
import com.shiropure.api.SplatoonSchedulesApi;
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
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.*;

import static com.shiropure.utils.DateUtil.HHformater;
import static com.shiropure.utils.SplatoonUtil.translateStage;

@handler()
public class SplatoonGuiMessageEventHandler extends GroupMessageEventHandler {
    public final String TUDI = "涂地";
    public final String ZHENGE = "真格";
    public final String XMODE = "x";
    public final String ZUPAI = "组排";
    public final String COOP = "打工";
    private Set<String> keywords;

    public SplatoonGuiMessageEventHandler() {
        //aa
        keywords = new HashSet<>(16);
        keywords.add(formateCommand(TUDI));
        keywords.add(formateCommand(ZHENGE));
        keywords.add(formateCommand(XMODE));
        keywords.add(formateCommand(ZUPAI));
        keywords.add(formateCommand(COOP));
    }

    @Override
    public List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx) {
        try {
            logger.info("message handled by baoziBot");
            SplatoonSchedules schedules = SplatoonSchedulesApi.SplatoonSchedules();
            String content = getPlantContent(event);
            if (content.startsWith(formateCommand(TUDI))) {
                logger.info("涂地模式查询");
                return tuDI(event, schedules);
            }
            if (content.startsWith(formateCommand(ZHENGE))) {
                logger.info("真格模式查询");
                return zhenGe(event, schedules);
            }
            if (content.startsWith(formateCommand(XMODE))) {
                logger.info("X模式查询");
                return X(event, schedules);
            }
            if (content.startsWith(formateCommand(ZUPAI))) {
                logger.info("组排模式查询");
                //return zuPai(event, schedules);
            }
            if (content.startsWith(formateCommand(COOP))) {
                logger.info("鲑鱼跑模式查询");
                //return coop(event, schedules);
            }
        } catch (Exception e) {
            logError(event, e);
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
        return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：not implement error");
    }

    private List<MessageChain> X(MessageEvent event, SplatoonSchedules schedules) throws IOException {
        String content = getPlantContent(event);
        String subcommand = content.substring(formateCommand(TUDI).length()).trim();
        int index = getIndex(subcommand);
        Schedules[] xSchedules = schedules.xSchedules;
        String time = "";
        time += HHformater(xSchedules[index].startTime.plusHours(8).getHour()) +":00-";
        time += HHformater(xSchedules[index].endTime.plusHours(8).getHour()) +":00";
        String name1 = translateStage(xSchedules[index].getStages()[0].stageName);
        String name2 =  translateStage(xSchedules[index].getStages()[1].stageName);
        String url1 =  translateStage(xSchedules[index].getStages()[0].stageUrl);
        String url2 =  translateStage(xSchedules[index].getStages()[1].stageUrl);
        String rule = xSchedules[0].getRule();
        HashMap<String,String> map = new HashMap<>();
        map.put("time",time);
        map.put("name1",name1);
        map.put("name2",name2);
        map.put("url1",url1);
        map.put("url2",url2);
        map.put("rule1",rule);
        String imagePath = ImageUtil.mapImgGenerator(map,"x");
        logger.info("包子功能完成");
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
        logger.info("包子功能完成");
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
        logger.info("包子功能完成");
        //上传生成文件，并发送
        return sendImage(imagePath,event);
    }
    public int getIndex(String command)
    {
        return Math.max(command.length()-1, 0);
    }
    public List<MessageChain> sendImage(String path,MessageEvent event) throws IOException {
        List<MessageChain> ans = new ArrayList<>();
        ExternalResource res = ExternalResource.create(new File(path));
        Image image = event.getSubject().uploadImage(res);
        res.close();
        ans.add(new MessageChainBuilder().append(image).build());
        return ans;
    }
    public boolean shouldHandle(MessageEvent event, Context ctx) {
        return startWithKeywords(event,keywords);
    }
}