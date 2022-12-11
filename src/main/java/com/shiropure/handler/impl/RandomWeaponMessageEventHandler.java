package com.shiropure.handler.impl;

import com.shiropure.handler.handler;
import com.shiropure.proxy.Context;
import com.shiropure.utils.OfUtil;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@handler
@SuppressWarnings("unchecked")
public class RandomWeaponMessageEventHandler extends GroupMessageEventHandler{
    public final String command1 = "随机武器";
    /**
     * 获取到群内需要此类处理的消息
     *
     * @param event 消息事件
     * @param ctx 消息内容
     * @return 返回消息内容
     */
    @Override
    public List<MessageChain> handleMessageEvent (MessageEvent event, Context ctx) {
        try {
            //获取随机数
            int random = (int)Math.floor(Math.random()*(66-1+1)+1);
            List<MessageChain> ans =new ArrayList<>();
            //获取随机图片
            ExternalResource res = ExternalResource.create(new File("./images/"+random+".png"));
            Image image = event.getSubject().uploadImage(res);
            res.close();
            //添加图片到回复消息序列，并构造消息
            ans.add(new MessageChainBuilder().append(image).build());
            return ans;
        }catch (Exception e){
            logError(event, e);
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
    }
    /**
     * 注册群内关键词，如果有注册的消息则返回True表示此类愿意处理此消息
     *
     * @param event 消息事件
     * @param ctx 消息内容
     * @return 是否愿意处理此消息
     */
    @Override
    public boolean shouldHandle(MessageEvent event, Context ctx) {
        return startWithKeywords(event, OfUtil.ofSet(formateCommand(command1),command1));
    }

}
