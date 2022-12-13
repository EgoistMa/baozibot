package com.shiropure.handler.impl;

import com.shiropure.Model.Schedules.SplatoonSchedules;
import com.shiropure.api.SplatoonSchedulesApi;
import com.shiropure.handler.handler;
import com.shiropure.proxy.Context;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.io.*;
import java.util.*;

@handler()
public class SwCodeMessageEventHandler extends GroupMessageEventHandler{
    public static final String regCode = "添加sw";
    public final String getCode = "我的sw";
    public static final String delCode = "删除sw";

    private Set<String> keywords;

    static HashMap<String,String> swCodes;
    public SwCodeMessageEventHandler() throws IOException {
        keywords = new HashSet<>(16);
        keywords.add(formateCommand(regCode));
        keywords.add(formateCommand(getCode));
        keywords.add(formateCommand(delCode));
        swCodes = loadCodes();
    }
    @Override
    public List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx) {
        try {
            logger.info("message handled by baoziBot");
            String content = getPlantContent(event);
            if(content.startsWith(formateCommand(regCode))) {
                logger.info("注册sw码");
                return regSwCode(event);
            }
            if(content.startsWith(formateCommand(getCode))) {
                logger.info("获取sw码");
                return getSwCode(event);
            }
            if(content.startsWith(formateCommand(delCode))) {
                logger.info("删除sw码");
                return delSwCode(event);
                //return buildMessageChainAsSingletonList("删除功能暂不可用，后续修复");
            }
        } catch (Exception e) {
            logError(event, e);
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
        return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：not implement error");
    }
    public static HashMap<String,String> loadCodes() throws IOException {
        HashMap outMap;
        try
        {
            FileInputStream fis = new FileInputStream("./assets/SWCodes.data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            outMap = (HashMap) ois.readObject();
            ois.close();
            fis.close();
            return outMap;
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
            return new HashMap<>();
        }catch(ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
    }
    public static void saveCodes() throws IOException{
        try{
            FileOutputStream fos =
                    new FileOutputStream("./assets/SWCodes.data");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(swCodes);
            oos.close();
            fos.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static String swRegCode (String qq,String code) throws IOException {
        swCodes.put(qq,code);
        saveCodes();
        return "馒头更新了你的sw码哦";
    }
    public static String swReadCode (String qq) throws IOException {
        String code = swCodes.get(qq);
        return qq == null ? "馒头不知道你的sw码哦" : code;
    }
    public static String delCode (String qq) throws IOException {
        swCodes.remove(qq);
        saveCodes();
        return "馒头删掉了哦";
    }
    private List<MessageChain> regSwCode(MessageEvent event) throws IOException {
        String qq = getSenderId(event);
        String content = getPlantContent(event);
        String SWcode = content.substring(formateCommand(regCode).length()).trim();
        //todo baozi功能
        return buildMessageChainAsSingletonList(swRegCode(qq,SWcode));
    }
    private List<MessageChain> getSwCode(MessageEvent event) throws IOException {
        String qq = getSenderId(event);
        //todo baozi功能
        return buildMessageChainAsSingletonList(swReadCode(qq));
    }
    private List<MessageChain> delSwCode(MessageEvent event) throws IOException {
        String qq = getSenderId(event);
        //todo baozi功能
        return buildMessageChainAsSingletonList(delCode(qq));
    }
    @Override
    public boolean shouldHandle(MessageEvent event, Context ctx) {
        return startWithKeywords(event,keywords);
    }
}
