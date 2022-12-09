package com.shiropure.utils;

import java.util.HashMap;
import java.util.Objects;

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
            put("Mahi-Mahi Resort", "鬼头刀度假区");
            put("Inkblot Art Academy", "海女美术大学");
            put("Sturgeon Shipyard", "鲟鱼造船厂");
            put("MakoMart", "座头购物中心");
            put("Wahoo World", "醋饭海洋世界");
            put("Gone Fission Hydroplant", "麦年海洋发电所");
            put("Spawning Grounds", "鲑坝");
            put("Sockeye Station", "新卷堡");
            put("Flounder Heights", "比目鱼住宅区");
            put("Brinewater Springs", "臭鱼干温泉");
        }
    };
    static HashMap<String,String> rule =  new HashMap<String, String>() {
        {
            put("Turf War", "占地对战");
            put("Tower Control", "真格塔楼");
            put("Rainmaker", "真格鱼虎对战");
            put("Splat Zones", "真格区域");
            put("Clam Blitz", "真格蛤蜊");
        }
    };
    static HashMap<String,String> weapons =  new HashMap<String, String>() {
        {
            put("48d6e062dd8b7efb","专业模型枪MG");
            put("3adcc67d997a5aa8","碳纤维滚筒");
            put("61d373353a48eb2e","爆炸泼桶");
            put("cfcd1cb4b09dc134","喷射清洁枪");
            put("7813d8c4d9103b07","洗笔桶");
            put("57684fd4ee281e09","斯普拉滚筒");
            put("7a4705bd110b0cee","满溢泡澡泼桶");
            put("ba2b27f7c17b1632","三发猎鱼弓");
            put("3939e000515fe04c","四重弹跳手枪 黑");
            put("6c5bbce4a6c63d0c","开瓶喷泉枪");
            put("39e383c2f6d1ca3e","鹦鹉螺号47");
            put("627d43fa3ab06066","快速爆破枪");
            put("4dc28c8d9bca2dae","斯普拉机动枪");
            put("670ae6f0c617cca4","火热爆破枪");
            put("9e45d86ccd5fea1e","圆珠笔");
            put("e2b5e6b340555596","高压油管枪");
            put("59d665d32bb4122a","窄域标记枪");
            put("77e4f6414e5a257d","雨刷刮水刀");
            put("2c34cb021254c8f8","顶尖射击枪");
            put("f668bd94e7ebf62d","快速爆破枪 精英");
            put("d3d579406dbc0fc8","专业模型枪MG");
            put("8c157e85b75798e7","碳纤维滚筒");
            put("6529a2c7f83ca858","爆炸泼桶");
            put("92f330252fdd9421","喷射清洁枪");
            put("7d97fc215efe47fa","洗笔桶");
            put("0637f4b4225f22b8","斯普拉滚筒");
            put("13ef7ad9ba855fe1","满溢泡澡泼桶");
            put("7ed9dd50b97d24f3","三发猎鱼弓");
            put("8e59d00fc471083a","四重弹跳手枪 黑");
            put("f09bb1bb306659dd","开瓶喷泉枪");
            put("af505e7c4bdb7888","鹦鹉螺号47");
            put("2d3900d357e005e9","快速爆破枪");
            put("582d03e42a63596a","斯普拉机动枪");
            put("9208eee9ecd2026e","火热爆破枪");
            put("cb9126ea2928b619","圆珠笔");
            put("70aa6b216ca8f01c","高压油管枪");
            put("41492e011c163cc1","窄域标记枪");
            put("df5b39ea3d32b25b","雨刷刮水刀");
            put("617dedf1c26235dc","顶尖射击枪");
            put("83275e416e7c1bc2","快速爆破枪 精英");
            put("1f6ce9f852641707","随机");
            put("d9bbd083353c118d",".52加仑");
            put("9a9dcaa55c2f6545","斯普拉射击枪");
            put("8c0617eafedab081","14式竹筒枪·甲");
            put("0037f260dd45e397","露营防空伞");
            put("cf6021b669c84379","N-ZAP85");
            put("b5c29680486b0d1c","巴勃罗");
            put("a99a6dd8efd4d5bb","H3卷管枪");
            put("50563f3849b68e0a","消防栓旋转枪");
            put("1e344559d62809b2","溅镀枪");
            put("e594a5f9535eaf40","北斋");
            put("7a2dd4e35809d537","电动马达滚筒");
            put("1e9e39f56c9f6d1a","鱿快洁α");
            put("5903a8cef02e4298","开尔文525");
            put("1208a614c4bb22cf","冲涂爆破枪");
            put("cb1243d9c1908a38","斯普拉旋转枪");
            put("57717b83b81f474f","公升4K");
            put("e70b1ea70b5916be","广域标记枪");
            put("4b6949b9e979636c","新星爆破枪");
            put("53cefbb18bb74cb3","特工配件");
            put("1d1dd4a9165c4a0c","斯普拉蓄力狙击枪");
            put("97745a7307013de3","双重清洁枪");
            put("11ba9e5928b14318","桶装旋转枪");
            put("5615e681937a3f12","L3卷管枪");
            put("24cab1bbfb443770","遮阳防空伞");
            put("ab50e9ae40810698",".96加仑");
            put("319a3174ebcb8224","新叶射击枪");
            put("a31def86914d98ae","回旋泼桶");
            put("afc40370eb8a1aa5","远距爆破枪");
            put("daa5ab5b571faec5","可变式滚筒");
            put("8f8bfbac8a43c2c7","工作刮水刀");
            put("70e60082b7f73c24","LACT-450");
            put("13d0d18880c2f4e4","飞溅泼桶");
            put("66e9cd75721a942f","太空射击枪");
            put("7f8967b3ae112ffc","宽滚筒");
            put("a1ea7028b1bcdd28","R-PEN/5H");
        }
    };
    public static String translateWeapon(String eng)
    {
        return weapons.getOrDefault(eng,eng);
    }
    public static String translateStage(String eng)
    {
        return stage.getOrDefault(eng, eng);
    }
    public static String translateRule(String eng)
    {
        return rule.getOrDefault(eng,eng);
    }
    public static String translateMode(String eng){ return eng.equals("CHALLENGE")? "挑战":"开放"; }
}
