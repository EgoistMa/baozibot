package com.shiropure.utils;
import com.shiropure.config.RobotConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class ImageUtil {
//    public static void main(String[] args) {
////        File f =new File("C:\\Users\\Administrator\\IdeaProjects\\t1\\1.png");
////        File f2=new File("C:\\Users\\Administrator\\IdeaProjects\\t1\\2.png");
////        File f3=new File("C:\\Users\\Administrator\\IdeaProjects\\t1\\3.png");
////        File f4=new File("C:\\Users\\Administrator\\IdeaProjects\\t1\\9.png");
////        File bgf=new File("C:\\Users\\Administrator\\IdeaProjects\\t1\\bg.png");
////
////        HashMap mod=new HashMap<String,String>();
////        mod.put("蛤蜊","C:\\Users\\Administrator\\IdeaProjects\\t1\\蛤2.png");
////        mod.put("区域","C:\\Users\\Administrator\\IdeaProjects\\t1\\区.png");
////        mod.put("鱼虎","C:\\Users\\Administrator\\IdeaProjects\\t1\\鱼.png");
////        mod.put("塔楼","C:\\Users\\Administrator\\IdeaProjects\\t1\\塔3.png");
////        String modname="塔楼";
////        String modname2="蛤蜊";
//////        String mod[][]=new String[4][4];
////        int y=663;
////
////        int modSize=330;
////        File modFile;
////        int modx=753-modSize/2;
//////        int mody=y+220;
////        int mody=420;
////
////        //地图名字统一设置
////        int mapNameSize=60;
////        String mapNameFont="汉仪铸字杂货铺W";
////        int  wordSpacing=65;
////
////
////
////        //时间
////        String timefont= "SplatoonFontFix";
////        String watermark="04:00-06:00";
////        String watermark2="04:00-06:00";
////        int timeFontSize=90;
////        int wmx=930;
//////        int wmy=y+80;
////        int wmy=280;
////
////       //上两张地图参数地图
////        int imgW=680;
////        int imgh=(int)(imgW/2.1);
////        int addx=753-imgW-5;
////        int addy=425;
////        //765
//////        int addy=232+y;
//////        int addy=755-imgh;
////
////
////        int cornerRadius=80;
////
////        //地图名字高度
////        int uy=395;
////        int dy=1060;
////
////        //左上地图名字
////        //6
//////      int urx=210;
////        int urx=210;
////        //右上地图名字
////        //6
//////      int ulx=905;
////        int ulx=905;
////       //5
//////        int xplus=45;
////        //3
//////        int xplus=100;
//////        int wordSpacingPlus=7;
////
////
////        String mapName ="鱼肉碎金属";
////        String mapName2="鲟鱼造船厂";
////        String mapName3="海女美术大学";
////        String mapName4="鳗鲶区";
////
//////下半部分
////
////
////        try{
//////            BufferedImage img=addImg(bgf,imageSize(f,720,360),70,400);
//////            BufferedImage simg=roundedCorner(splicingPictures(imageSize(f,720,360),imageSize(f2,720,360),10),20);
////            //添加前两张地图图片
////            BufferedImage finalImg=addImg(bgf,roundedCorner(splicingPictures(imageSize(f,imgW,imgh),imageSize(f2,imgW,imgh),10),cornerRadius),addx,addy);
////            //上半部分时间
////            finalImg=addWatermark(finalImg,watermark,timefont,timeFontSize,wmx,wmy);
////            //上半部分模式标志
////            modFile= new File((String) mod.get(modname));
////            finalImg=addImg(finalImg,imageSize(modFile,modSize,modSize),modx,mody);
////
////            //下半部分
//////            BufferedImage Img=roundedCorner(splicingPictures(imageSize(f3,imgW,imgh),imageSize(f4,imgW,imgh),10),cornerRadius);
////            finalImg=addImg(finalImg,roundedCorner(splicingPictures(imageSize(f3,imgW,imgh),imageSize(f4,imgW,imgh),10),cornerRadius),addx,addy+y);
////
////            finalImg= finalImg=addWatermark(finalImg,watermark2,timefont,timeFontSize,wmx,wmy+y);
////
////            modFile= new File((String) mod.get(modname2));
////            finalImg=addImg(finalImg,imageSize(modFile,modSize,modSize),modx,mody+y);
////
////
////            //地图文字部分
////            //左上
////            finalImg=addWatermark(finalImg,mapName,mapNameFont,mapNameSize,urx+getxPlus(mapName.length()),uy,wordSpacing+getWordSpacingPlus(mapName.length()));
////            //右上
////            finalImg=addWatermark(finalImg,mapName2,mapNameFont,mapNameSize,ulx+getxPlus(mapName2.length()),uy,wordSpacing+getWordSpacingPlus(mapName2.length()));
////            //左下
////            finalImg=addWatermark(finalImg,mapName3,mapNameFont,mapNameSize,urx+getxPlus(mapName3.length()),dy,wordSpacing+getWordSpacingPlus(mapName3.length()));
////            //右d
////            finalImg=addWatermark(finalImg,mapName4,mapNameFont,mapNameSize,ulx+getxPlus(mapName4.length()),dy,wordSpacing+getWordSpacingPlus(mapName4.length()));
////
////
////
////
////            File newFile = new File("C:\\Users\\Administrator\\IdeaProjects\\t1\\ttt.png");
////            ImageIO.write(finalImg, "png", newFile);
////            System.out.println("爬");
////
////        }catch (Exception e){
////            System.out.println("会写代码吗？");
////        }
//
//        String mod="regular";
//        HashMap map=new HashMap<String,String>();
//        map.put("url1","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/40aba8b36a9439e2d670fde5b3478819ea8a94f9e503b9d783248a5716786f35_1.png");
//        map.put("url2","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/488017f3ce712fca9fb37d61fe306343054449bb2d2bb1751d95f54a98564cae_1.png");
//        map.put("url3","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/40aba8b36a9439e2d670fde5b3478819ea8a94f9e503b9d783248a5716786f35_1.png");
//        map.put("url4","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/488017f3ce712fca9fb37d61fe306343054449bb2d2bb1751d95f54a98564cae_1.png");
//        map.put("time","04:00-06:00");
//        map.put("rule1","Clam Blitz");
//        map.put("rule2","Rainmaker");
//        map.put("name1","鱼肉碎金属");
//        map.put("name2","鳗鲶区");
//        map.put("name3","鱼肉碎金属");
//        map.put("name4","鳗鲶区");
//
//        mapImgGenerator(map,mod);
//
//
//
//    }

    public static String mapImgGenerator(HashMap map, String mod){
        HashMap rule=new HashMap<String,String>();
        rule.put("Clam Blitz","./images/imagesBackground/蛤2.png");
        rule.put("Splat Zones","./images/imagesBackground/区.png");
        rule.put("Rainmaker","./images/imagesBackground/鱼.png");
        rule.put("Tower Control","./images/imagesBackground/塔3.png");
        String outImg="./images/imagesBackground/t.png";

        int y=663;
        int modSize=330;
        File modFile;
        int modx=753-modSize/2;
//        int mody=y+220;
        int mody=420;
        //地图名字统一设置
        int mapNameSize=60;
        String mapNameFont="汉仪铸字杂货铺W";
        int  wordSpacing=65;
        //时间
        String timefont= "SplatoonFontFix";
        int timeFontSize=90;
        int wmx=930;
//        int wmy=y+80;
        int wmy=280;
        //上两张地图参数地图
        int imgW=680;
        int imgh=(int)(imgW/2.1);
        int addx=753-imgW-5;
        int addy=425;
        //765
//        int addy=232+y;
//        int addy=755-imgh;
        int cornerRadius=80;
        //地图名字高度
        int uy=395;
        int dy=1060;
        //左上地图名字
        //6
//      int urx=210;
        int urx=210;
        //右上地图名字
        //6
//      int ulx=905;
        int ulx=905;
        //5
//        int xplus=45;
        //3
//        int xplus=100;
//        int wordSpacingPlus=7;
        String watermark= (String)map.get("time");
        try {
            if (mod == "bankara") {
                //真格部分
                File bgf = new File("./images/imagesBackground/bg.png");
                String rule1 = (String) map.get("rule1");
                String rule2 = (String) map.get("rule2");
                String mapName = (String) map.get("name1");
                String mapName2 = (String) map.get("name2");
                String mapName3 = (String) map.get("name3");
                String mapName4 = (String) map.get("name4");
                URL f = new URL((String) map.get("url1"));
                URL f2 = new URL((String) map.get("url2"));
                URL f3 = new URL((String) map.get("url3"));
                URL f4 = new URL((String) map.get("url4"));
                BufferedImage finalImg = addImg(bgf, roundedCorner(splicingPictures(imageSize(f, imgW, imgh), imageSize(f2, imgW, imgh), 10), cornerRadius), addx, addy);
                //上半部分时间
                finalImg = addWatermark(finalImg, watermark, timefont, timeFontSize, wmx, wmy);
                //上半部分模式标志
                modFile = new File((String) rule.get(rule1));
                finalImg = addImg(finalImg, imageSize(modFile, modSize, modSize), modx, mody);
                //下半部分
//            BufferedImage Img=roundedCorner(splicingPictures(imageSize(f3,imgW,imgh),imageSize(f4,imgW,imgh),10),cornerRadius);
                finalImg = addImg(finalImg, roundedCorner(splicingPictures(imageSize(f3, imgW, imgh), imageSize(f4, imgW, imgh), 10), cornerRadius), addx, addy + y);
                finalImg = finalImg = addWatermark(finalImg, watermark, timefont, timeFontSize, wmx, wmy + y);
                modFile = new File((String) rule.get(rule2));
                finalImg = addImg(finalImg, imageSize(modFile, modSize, modSize), modx, mody + y);
                //地图文字部分
                //左上
                finalImg = addWatermark(finalImg, mapName, mapNameFont, mapNameSize, urx + getxPlus(mapName.length()), uy, wordSpacing + getWordSpacingPlus(mapName.length()));
                //右上
                finalImg = addWatermark(finalImg, mapName2, mapNameFont, mapNameSize, ulx + getxPlus(mapName2.length()), uy, wordSpacing + getWordSpacingPlus(mapName2.length()));
                //左下
                finalImg = addWatermark(finalImg, mapName3, mapNameFont, mapNameSize, urx + getxPlus(mapName3.length()), dy, wordSpacing + getWordSpacingPlus(mapName3.length()));
                //右d
                finalImg = addWatermark(finalImg, mapName4, mapNameFont, mapNameSize, ulx + getxPlus(mapName4.length()), dy, wordSpacing + getWordSpacingPlus(mapName4.length()));

                File newFile = new File(outImg);
                ImageIO.write(finalImg, "png", newFile);
                //System.out.println("爬");
            } else if (mod == "x") {
                File bgf = new File("./images/imagesBackground/bgm2.png");
                String rule1 = (String) map.get("rule1");
                String mapName = (String) map.get("name1");
                String mapName2 = (String) map.get("name2");
                URL f = new URL((String) map.get("url1"));
                URL f2 = new URL((String) map.get("url2"));

                BufferedImage finalImg = addImg(bgf, roundedCorner(splicingPictures(imageSize(f, imgW, imgh), imageSize(f2, imgW, imgh), 10), cornerRadius), addx, addy);
                //上半部分时间
                finalImg = addWatermark(finalImg, watermark, timefont, timeFontSize, wmx, wmy);
                //上半部分模式标志
                modFile = new File((String) rule.get(rule1));
                finalImg = addImg(finalImg, imageSize(modFile, modSize, modSize), modx, mody);
                finalImg = addWatermark(finalImg, mapName, mapNameFont, mapNameSize, urx + getxPlus(mapName.length()), uy, wordSpacing + getWordSpacingPlus(mapName.length()));
                //右上
                finalImg = addWatermark(finalImg, mapName2, mapNameFont, mapNameSize, ulx + getxPlus(mapName2.length()), uy, wordSpacing + getWordSpacingPlus(mapName2.length()));
                File newFile = new File(outImg);
                ImageIO.write(finalImg, "png", newFile);
                //System.out.println("爬");
            } else {
                //涂地
//            File f =new File((String)map.get("url1"));
//            File f2=new File((String)map.get("url2"));
                File bgf = new File("./images/imagesBackground/bgm3.png");
                String mapName = (String) map.get("name1");
                String mapName2 = (String) map.get("name2");
                URL f = new URL((String) map.get("url1"));
                URL f2 = new URL((String) map.get("url2"));
                BufferedImage finalImg = addImg(bgf, roundedCorner(splicingPictures(imageSize(f, imgW, imgh), imageSize(f2, imgW, imgh), 10), cornerRadius), addx, addy);
                //上半部分时间
                finalImg = addWatermark(finalImg, watermark, timefont, timeFontSize, wmx, wmy);
                finalImg = addWatermark(finalImg, mapName, mapNameFont, mapNameSize, urx + getxPlus(mapName.length()), uy, wordSpacing + getWordSpacingPlus(mapName.length()));
                //右上
                finalImg = addWatermark(finalImg, mapName2, mapNameFont, mapNameSize, ulx + getxPlus(mapName2.length()), uy, wordSpacing + getWordSpacingPlus(mapName2.length()));
                File newFile = new File(outImg);
                ImageIO.write(finalImg, "png", newFile);
                //System.out.println("爬");
            }
            return outImg;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    //自定义图片尺寸 参数 File 图片宽度 图片高度
    public static BufferedImage imageSize(File f, int w, int h) throws IOException {
        BufferedImage img =ImageIO.read(f);
        BufferedImage newImg=new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB);
        newImg.getGraphics().drawImage(img,0,0,w,h,null);
        return newImg;
    }
    public static BufferedImage imageSize(URL f, int w, int h) throws IOException {
        BufferedImage img =ImageIO.read(f);
        BufferedImage newImg=new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB);
        newImg.getGraphics().drawImage(img,0,0,w,h,null);
        return newImg;
    }
    // File(原图)  BufferedImage 添加进去的图片 x y添加图片的坐标
    public static BufferedImage addImg(File f, BufferedImage addimg, int x, int y) throws IOException{
        BufferedImage backgroundImage = ImageIO.read(f);
        Graphics2D pen = backgroundImage.createGraphics();
        pen.drawImage(addimg ,null,x,y);
//        System.out.println(backgroundImage.getWidth());
//        System.out.println(backgroundImage.getHeight());
        return backgroundImage;
    }
    public static BufferedImage addImg( BufferedImage bgimg,File f, int x, int y) throws IOException{
        BufferedImage addimg = ImageIO.read(f);
        Graphics2D pen = bgimg.createGraphics();
        pen.drawImage(addimg ,null,x,y);
        return bgimg;
    }

    //原图  添加的图片  坐标
    public static BufferedImage addImg(BufferedImage img, BufferedImage addimg, int x, int y) throws IOException{
        Graphics2D pen = img.createGraphics();
        pen.drawImage(addimg ,null,x,y);
        return img;
    }
    public static int getxPlus(int a){
        if (a>5){
            return 0;
        }else if(a>4){
            return 45;
        }else {
            return 100;
        }

    }

    public static int getWordSpacingPlus(int a){
        if(a!=3){
            return 0;
        }else return 7;
    }
    //图片拼接
    public static  BufferedImage splicingPictures(File f1,File f2,int a1)throws IOException{
        BufferedImage img1= ImageIO.read(f1);
        BufferedImage img2= ImageIO.read(f2);
        BufferedImage a=new BufferedImage(a1,img1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        int[][] ImageArrays = new int[3][];

        ImageArrays[0] = new int[img1.getHeight() * img1.getWidth()];
        ImageArrays[0] = img1.getRGB(0, 0,img1.getWidth(), img1.getHeight(), ImageArrays[0], 0, img1.getWidth());

        ImageArrays[1] = new int[img1.getHeight() *a1];

        ImageArrays[2] = new int[img2.getHeight() * img2.getWidth()];
        ImageArrays[2] = img2.getRGB(0, 0,img2.getWidth(), img2.getHeight(), ImageArrays[2], 0, img2.getWidth());

        BufferedImage newImg = new BufferedImage(img1.getWidth()+img2.getWidth()+a1,img1.getHeight(), BufferedImage.TYPE_INT_ARGB);

        newImg.setRGB(0,0,img1.getWidth(),img1.getHeight(),ImageArrays[0],0,img1.getWidth());

        newImg.setRGB(img1.getWidth()+a.getWidth(),0,img2.getWidth(),img1.getHeight(),ImageArrays[2],0,img2.getWidth());
        return newImg;
    }
    public static  BufferedImage splicingPictures(BufferedImage img1,BufferedImage img2,int a1)throws IOException{
        BufferedImage a=new BufferedImage(a1,img1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        int[][] ImageArrays = new int[3][];

        ImageArrays[0] = new int[img1.getHeight() * img1.getWidth()];
        ImageArrays[0] = img1.getRGB(0, 0,img1.getWidth(), img1.getHeight(), ImageArrays[0], 0, img1.getWidth());

        ImageArrays[1] = new int[img1.getHeight() *a1];

        ImageArrays[2] = new int[img2.getHeight() * img2.getWidth()];
        ImageArrays[2] = img2.getRGB(0, 0,img2.getWidth(), img2.getHeight(), ImageArrays[2], 0, img2.getWidth());

        BufferedImage newImg = new BufferedImage(img1.getWidth()+img2.getWidth()+a1,img1.getHeight(), BufferedImage.TYPE_INT_ARGB);

        newImg.setRGB(0,0,img1.getWidth(),img1.getHeight(),ImageArrays[0],0,img1.getWidth());

        newImg.setRGB(img1.getWidth()+a.getWidth(),0,img2.getWidth(),img1.getHeight(),ImageArrays[2],0,img2.getWidth());
        return newImg;
    }
    public static BufferedImage roundedCorner(BufferedImage image,int cornerRadius){

        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius,cornerRadius));
        //g2.fill(new RoundRectangle2D.Float(40, 0, w, h, cornerRadius,cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        byte end=0;
        f1:for(int i=w/4;i<w;i++){
            f2:  for (int j=0;j<h;j++){
                if(output.getRGB(i,j)==-1){
                    output.setRGB(i,j,0);
                    end=1;
                }else if(end==1){
                    break f1;
                }else{
                    break f2;
                }

            }
        }
        g2.dispose();
        return output;
    }

    public static BufferedImage addWatermark(BufferedImage img,String Watermark,String font,int size,int x,int y){


        Graphics2D pen = img.createGraphics();
        pen.setColor(Color.WHITE);
        pen.setFont(new Font(font, Font.PLAIN, size));

        pen.drawString(Watermark, x, y);

        return img;
    }
    public static BufferedImage addWatermark(BufferedImage img,String Watermark,String font,int size,int x,int y,int wordSpacing){

        Graphics2D pen = img.createGraphics();
        pen.setColor(Color.WHITE);
        pen.setFont(new Font(font, Font.PLAIN, size));
        int n=Watermark.length();
        for(int i=0;i<Watermark.length();i++){

            pen.drawString(Watermark.substring(i,i+1), x, y);
            x+=wordSpacing;

        }

        return img;
    }
}

