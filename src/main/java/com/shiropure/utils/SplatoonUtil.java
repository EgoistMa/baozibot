package com.shiropure.utils;

import java.util.HashMap;
import java.util.Map;

public class SplatoonUtil {
    static HashMap<String,String> stage =  new HashMap<String, String>() {
        {
            put("Scorch Gorge", "温泉花大峡谷");
            put("Eeltail Alley", "鳗鲶区");
            put("Hagglefish Market", "烟管鱼市场");
            put("Undertow Spillway", "竹蛏疏洪道");
            put("Mincemeat Metalworks", "鱼肉碎金属");
            put("Hammerhead Bridge", "真鲭跨海大桥");
            put("Museum d'Alfonsino", "金眼鲷美术馆");
            put("Mahi-Mahi Resort", "鬼头刀SPA度假区");
            put("Inkblot Art Academy", "海女美术大学");
            put("Sturgeon Shipyard", "鲟鱼造船厂");
            put("MakoMart", "座头购物中心");
            put("Wahoo World", "醋饭海洋世界");
            put("Gone Fission Hydroplant", "麦年海洋发电所");
            put("Spawning Grounds", "鲑坝");
            put("Sockeye Station", "新卷堡");
        }
    };
    static HashMap<String,String> stageImage =  new HashMap<String, String>() {
        {
            put("温泉花大峡谷","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/1db8ab338b64b464df50e7f9e270e59423ff8caac6f09679a24f1b7acf3a82f3_1.png");
            put("鳗鲶区","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/898e1ae6c737a9d44552c7c81f9b710676492681525c514eadc68a6780aa52af_1.png");
            put("烟管鱼市场","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/8dc2f16d39c630bab40cead5b2485ca3559e829d0d3de0c2232c7a62fefb5fa9_1.png");
            put("竹蛏疏洪道","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/9b1c17b2075479d0397d2fb96efbc6fa3a28900712920e5fe1e9dfc59c6abc5c_1.png");
            put("鱼肉碎金属","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/de1f212e9ff0648f36cd3b8e0917ef36b3bd51445159297dcb948f34a09f2f05_1.png");
            put("真鲭跨海大桥","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/1db8ab338b64b464df50e7f9e270e59423ff8caac6f09679a24f1b7acf3a82f3_1.png");
            put("金眼鲷美术馆","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/b9d8cfa186d197a27e075600a107c99d9e21646d116730f0843e0fff0aaba7dd_1.png");
            put("鬼头刀SPA度假区","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/8273118c1ffe1bf6fe031c7d8c9795dab52632c9b76e8e9f01f644ac5ae0ccc0_1.png");
            put("海女美术大学","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/40aba8b36a9439e2d670fde5b3478819ea8a94f9e503b9d783248a5716786f35_1.png");
            put("鲟鱼造船厂","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/48684c69d5c5a4ffaf16b712a4895545a8d01196115d514fc878ce99863bb3e9_1.png");
            put("座头购物中心","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/a8ba96c3dbd015b7bc6ea4fa067245c4e9aee62b6696cb41e02d35139dd21fe7_1.png");
            put("醋饭海洋世界","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/61ea801fa4ed32360dcaf83986222ded46a72dbf56194acc6d0cf4659a92ba85_1.png");
            put("麦年海洋发电所","https://splatoon3.ink/assets/splatnet/stage_img/icon/high_resolution/f1e4df4cff1dc5e0acc66a9654fecf949224f7e4f6bd36305d4600ac3fa3db7b_0.png");
            put("鲑坝","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/be584c7c7f547b8cbac318617f646680541f88071bc71db73cd461eb3ea6326e_1.png");
            put("新卷堡","https://splatoon3.ink/assets/splatnet/stage_img/icon/low_resolution/3418d2d89ef84288c78915b9acb63b4ad48df7bfcb48c27d6597920787e147ec_1.png");
        }
    };
    static HashMap<String,String> mode =  new HashMap<String, String>() {
        {
            put("Turf War", "占地对战");
            put("Tower Control", "真格塔楼");
            put("Rainmaker", "真格鱼虎对战");
            put("Splat Zones", "真格区域");
            put("Clam Blitz", "真格蛤蜊");
        }
    };
    public static String translateStage(String eng)
    {
        return stage.get(eng);
    }
    public static String translateMode(String eng)
    {
        return mode.get(eng);
    }
    public static String translateRule(String eng){ return eng.equals("CHALLENGE")? "挑战":"开放"; }
    public static String getStageImg(String stage) {
        return stageImage.get(stage);
    }
}
