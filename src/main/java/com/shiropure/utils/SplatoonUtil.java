package com.shiropure.utils;

import java.util.HashMap;
import java.util.Map;

public class SplatoonUtil {
    static HashMap<String,String> translateMap =  new HashMap<String, String>() {
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
    public static String translateStage(String eng)
    {
        return translateMap.get(eng);
    }
}
