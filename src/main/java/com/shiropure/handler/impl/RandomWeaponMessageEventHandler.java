package com.shiropure.handler.impl;

import com.shiropure.handler.handler;
import com.shiropure.proxy.Context;
import com.shiropure.utils.ImageUtil;
import com.shiropure.utils.OfUtil;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.utils.ExternalResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@handler
public class RandomWeaponMessageEventHandler extends GroupMessageEventHandler{
    public final String command1 = "随机武器";
    public final String command2 = "私房随机";
    /**
     * 获取到群内需要此类处理的消息
     *
     * @param event 消息事件
     * @param ctx 消息内容
     * @return 返回消息内容
     */
    @Override
    public List<MessageChain> handleMessageEvent (MessageEvent event, Context ctx) {
        String content = getPlantContent(event);
        try {
            if (content.startsWith(formateCommand(command1))) {
                logger.info("随机武器");
                return randomWeapon(event);
            }
            if (content.startsWith(formateCommand(command2))) {
                logger.info("私房随机");
                String imagePath = civilWarRandomWeapon(2,1,1);
                return sendImage(imagePath,event);
            }
        }catch (Exception e){
            logError(event, e);
            return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：" + e.getMessage());
        }
        return buildMessageChainAsSingletonList("发生了意料之外、情理之中的错误：not implement error" );
    }
    public List<MessageChain> randomWeapon(MessageEvent event) throws IOException {
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
    }
    public List<MessageChain> sendImage(String path,MessageEvent event) throws IOException {
        List<MessageChain> ans = new ArrayList<>();
        ExternalResource res = ExternalResource.create(new File(path));
        Image image = event.getSubject().uploadImage(res);
        res.close();
        ans.add(new MessageChainBuilder().append(image).build());
        return ans;
    }
    public static  String civilWarRandomWeapon(int frontCourt,int middleCourt,int backCourt){
        if(frontCourt+middleCourt+backCourt!=4) {logger.info("输入的数值有问题qwq");return "";}
        int weaponNum=frontCourt+middleCourt+backCourt;
        String out ="./images/imagesBackground/randomWeapon.png";
        String yteam[] =new String[4];
        String pteam[] =new String[4];
        byte j=0;



        //两队后排
        for (int i=0;i<backCourt;i++) {
            yteam[j] = "images/imagesBackground/武器普通/后排/" + (int) Math.floor(Math.random() * (12 - 1 + 1) + 1) + ".png";
            pteam[j] = "images/imagesBackground/武器普通/后排/" + (int) Math.floor(Math.random() * (12 - 1 + 1) + 1) + ".png";
            j++;
        }

        //两队中排
        for (int i=0;i<middleCourt;i++) {
            yteam[j] = "images/imagesBackground/武器普通/中排/" + (int) Math.floor(Math.random() * (18 - 1 + 1) + 1) + ".png";
            pteam[j] = "images/imagesBackground/武器普通/中排/" + (int) Math.floor(Math.random() * (18 - 1 + 1) + 1) + ".png";
            j++;
        }
        //两队前排
        for (int i=0;i<frontCourt;i++) {
            yteam[j] = "images/imagesBackground/武器普通/前排/" + (int) Math.floor(Math.random() * (38 - 1 + 1) + 1) + ".png";
            pteam[j] = "images/imagesBackground/武器普通/前排/" + (int) Math.floor(Math.random() * (38 - 1 + 1) + 1) + ".png";
            j++;
        }

        //打乱数组顺序
        Collections.shuffle(Arrays.asList(yteam));
        Collections.shuffle(Arrays.asList(pteam));
//    yteam=weaponSort(yteam);
//    pteam=weaponSort(pteam);
//    for (int i=0;i<4;i++) {
//        System.out.println(yteam[i]);
//    }

        weaponImgOut(yteam,pteam,weaponNum,out);

        return out;
    }

    //    //武器排序
//    public static String[] weaponSort(String[] out){
//    int a[]=new int[4];
//    String b=null;
//    int c=0;
//
//    for (int i=0;i<4;i++){
//         a[i]=(int) Math.floor(Math.random() * (1000 - 1 + 1) + 1);
//    }
//    //排序
//    for(int i=3;i>=0;i--){
//        for(int j=i-1;j>=0;j--){
//            if(a[i]<a[j]){
//                c=a[i];
//                a[i]=a[j];
//                a[j]=c;
//                b=out[i];
//                out[i]=out[j];
//                out[j]=b;
//            }
//        }
//    }
//
//
//
//    return out;
//    }
//
    public static void weaponImgOut(String[]y,String[]p,int weaponNum,String out){
        int imgY[]=new int[2];
        int imgX[]=new int[4];
        int imgSize=300;
        imgY[0]=200;
        imgY[1]=700;
        imgX[0]=225-150;
        imgX[1]=675-150;
        imgX[2]=1125-150;
        imgX[3]=1575-150;

        try{
            File bg=new File("images/imagesBackground/rwbg2.png");
            BufferedImage[] wimg=new BufferedImage[weaponNum*2];
            BufferedImage bgimg= ImageIO.read(bg);
            ImageUtil img1= new ImageUtil();
            //统一图片尺寸
            for (int i=0;i<weaponNum;i++){
                wimg[i]=img1.imageSize(new File(y[i]),imgSize,imgSize);
                wimg[i+weaponNum]=img1.imageSize(new File(p[i]),imgSize,imgSize);

            }
            //添加至背景图里
            for (int i=0;i<weaponNum;i++){
                bgimg=img1.addImg(bgimg,wimg[i],imgX[i],imgY[0]);
                bgimg=img1.addImg(bgimg,wimg[i+weaponNum],imgX[i],imgY[1]);
            }
            img1.imgGenerator(bgimg,out);
        }catch (Exception e){
            e.printStackTrace();
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
        return startWithKeywords(event, OfUtil.ofSet(formateCommand(command1),formateCommand(command2)));
    }
}
