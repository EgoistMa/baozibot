package com.shiropure.handler.impl;

import com.shiropure.Model.Schedules.SplatoonSchedules;
import com.shiropure.api.SplatoonSchedulesApi;
import com.shiropure.handler.handler;
import com.shiropure.proxy.Context;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@handler()
@SuppressWarnings("unchecked")
public class HelperMessageEventHandler extends GroupMessageEventHandler {
    public final String HELP = "#";
    private Set<String> keywords;
    public HelperMessageEventHandler()
    {
        keywords = new HashSet<>(16);
        keywords.add(HELP);
    }
    public List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx) {
        try {
            logger.info("message handled by baoziBot");
            String content = getPlantContent(event);
            if(content.startsWith(HELP)) {
                logger.info("帮助");
                return getHelp(event);
            }
        } catch (Exception e) {
            logError(event, e);
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
        return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：not implement error");
    }

    public List<MessageChain> getHelp(MessageEvent event){
        //获取子命令
        String content = getPlantContent(event);
        String subCommand = content.substring(HELP.length()).trim();
        subCommand = subCommand.toUpperCase();
        //回复消息构造器
        List<MessageChain> ans = new ArrayList<>();
        MessageChainBuilder mc= new MessageChainBuilder();
        //logger.info(subCommand);
        if(subCommand.equals("HELP") || subCommand.equals("帮助"))
        {
            mc.append(
                    "馒头bot使用指南\n" +
                            "查询当前涂地:            ，涂地 （下次）（下下次）...\n" +
                            "查询当前真格挑战和开放:    ，真格（下次）（下下次）...\n" +
                            "查询当前组排:            ，组排 （下次）（下下次）...\n" +
                            "查询全部x赛:             ，x （下次）（下下次）...\n" +
                            "查询鲑鱼跑 :             ，打工 (全部)\n" +
                            "-----------------------------------\n" +
                            "随机抽取一个武器          ，随机武器\n" +
                            "私房随机武器              ，私房随机\n" +
                            "-----------------------------------\n" +
                            "保存我的sw码              ，添加sw\n" +
                            "获取我的sw码              ，我的sw\n" +
                            "删除我的sw码              ，删除sw\n" +
                            "\n" +
                            "\n" +
                            "还在开发中项目......\n" +
                            "\n" +
                            "鱿鱼须商店\n" +
                            "\n" +
                            "有什么需要的功能可以提，能力有限尽量qwq\n");
            ans.add(mc.build());
            return ans;
        }else{
            logger.info("未知的子命令， 忽略消息，请尝试 #help");
            return null;
        }
    }
    @Override
    public boolean shouldHandle(MessageEvent event, Context ctx) {
        return startWithKeywords(event,keywords);
    }
}
