package com.shiropure.handler.impl;

import com.shiropure.Model.Schedules.SplatoonSchedules;
import com.shiropure.api.SplatoonSchedulesApi;
import com.shiropure.handler.handler;
import com.shiropure.proxy.Context;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@handler()
public class SwCodeMessageEventHandler extends GroupMessageEventHandler{
    public static final String regCode = "添加sw";
    public final String getCode = "我的sw";
    public static final String delCode = "删除sw";

    private Set<String> keywords;

    public SwCodeMessageEventHandler()
    {
        keywords = new HashSet<>(16);
        keywords.add(formateCommand(regCode));
        keywords.add(formateCommand(getCode));
        keywords.add(formateCommand(delCode));
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
            }
        } catch (Exception e) {
            logError(event, e);
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
        return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：not implement error");
    }


    //sw码查询方法 参数（qq号） 返回sw码
    public static String swRead(String user){
        try {
            String path="./assets/sw码.txt";
            int userlenth=user.length();

            BufferedReader b1=new BufferedReader(new FileReader(path));
            StringBuffer s1=new StringBuffer();

//            s1.append("332883417");
//
//            System.out.println(s1.toString().substring(0,userlenth).equals(user));
            for (;b1.ready();)
            {
                s1.append(b1.readLine());
//                System.out.println(s1);
                //判断这一行是否和user相同
                if(s1.toString().substring(0,userlenth).equals(user)){
                    //判断后面是否是冒号（是冒号则完全相同）
                    if (s1.toString().substring(userlenth,userlenth+1).equals(":")){
//                        System.out.println(s1.toString().substring(userlenth+1));
                        b1.close();
                        //返回查询结果
                        return s1.toString().substring(userlenth+1);
                    }
                }
                s1.delete(0,s1.length());
            }
            return "您还没有添加过sw码，使用“"+formateCommand(regCode)+"”来试试吧";
        }catch (Exception e){e.printStackTrace();}return "有问题";}


    public static String swWrite(String user ,String sw){
        try {

            //如果查询不到这个用户存储过sw码
            if(swRead(user).startsWith("您还没有添加过sw码")){
                String path="./assets/sw码.txt";
//            String user="12312331212";
//            String sw="000000000001";
                BufferedWriter b1=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,true)));
                b1.newLine();
                b1.write(user+":"+sw);
                b1.flush();
                b1.close();
                return "添加sw成功";
            }else {
                return "已经添加了sw码 ,如果需要修改请先使用“"+formateCommand(delCode)+"”删除";
            }
        }catch (Exception e){e.printStackTrace();}
        return "出错了";
    }

    private List<MessageChain> regSwCode(MessageEvent event) {
        String qq = getSenderId(event);
        String content = getPlantContent(event);
        String SWcode = content.substring(formateCommand(regCode).length()).trim();
        //todo baozi功能
        return buildMessageChainAsSingletonList(swWrite(qq,SWcode));
    }
    private List<MessageChain> getSwCode(MessageEvent event) {
        String qq = getSenderId(event);
        //todo baozi功能
        return buildMessageChainAsSingletonList(swRead(qq));
    }
    private List<MessageChain> delSwCode(MessageEvent event) {
        String qq = getSenderId(event);
        //todo baozi功能
        return buildMessageChainAsSingletonList(swDel(qq));
    }
    public static String swDel(String user){
        try {
            String path="./assets/sw码.txt";
            int userlenth=user.length();
            BufferedReader b1=new BufferedReader(new FileReader(path));
            StringBuffer s1=new StringBuffer();
            StringBuffer s2=new StringBuffer();
            int line=-1;
            int i=0;
            for (;b1.ready();i++)
            {
                s1.append(b1.readLine());
                s2.append("!"+s1);
//                System.out.println(s2);
                //判断这一行是否和user相同
                if(s1.toString().substring(0,userlenth).equals(user)){
                    //判断后面是否是冒号（是冒号则完全相同）
                    if (s1.toString().substring(userlenth,userlenth+1).equals(":")){
                        //记录位置
                        line=i;
                    }
                }
                s1.delete(0,s1.length());
            }
            if(line==-1) return "您还没添加过sw码呐";
            BufferedWriter b2=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
            System.out.println(s2);
            for (int j=0;j<i;j++){
                //剔除第一个“！”前的内容
                s1.append(s2.substring(s2.indexOf("!")+1));
                s2.delete(0,s2.length());
                s2.append(s1);
                s1.delete(0,s1.length());
                if (j==line)continue;
                //判断是否为最后一行sw码
                if(s2.indexOf("!")!=-1){
                    b2.write(s2.substring(0,s2.indexOf("!")));
                    b2.newLine();
                    b2.flush();
                } else {
                    b2.write(s2.substring(0));
                    b2.flush();
                }
            }
            b2.close();
            return "删除成功";
        }catch (Exception e){e.printStackTrace();}
        return "删除出错啦！";
    }
    @Override
    public boolean shouldHandle(MessageEvent event, Context ctx) {
        return startWithKeywords(event,keywords);
    }
}
