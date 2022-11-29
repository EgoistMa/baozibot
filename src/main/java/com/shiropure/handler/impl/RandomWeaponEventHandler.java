package com.shiropure.handler.impl;

import com.shiropure.exception.FileUploadException;
import com.shiropure.handler.handler;
import com.shiropure.proxy.Context;
import com.shiropure.utils.OfUtil;
import net.mamoe.mirai.contact.AnonymousMember;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@handler
@SuppressWarnings("unchecked")
public class RandomWeaponEventHandler extends GroupMessageEventHandler{
    public final String command1 = "随机武器";
    @Override
    public List<MessageChain> handleMessageEvent (MessageEvent event, Context ctx) {
        try {
            int random = (int)Math.floor(Math.random()*(53-1+1)+1);
            List<MessageChain> ans =new ArrayList<>();
            ExternalResource res = ExternalResource.create(new File("./images/"+random+".png"));
            Image image = event.getSubject().uploadImage(res);
            res.close();
            ans.add(new MessageChainBuilder().append(image).build());
            return ans;
        }catch (Exception e){
            logError(event, e);
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
 }
    @Override
    public boolean shouldHandle(MessageEvent event, Context ctx) {
        return startWithKeywords(event, OfUtil.ofSet(command1));
    }

}
