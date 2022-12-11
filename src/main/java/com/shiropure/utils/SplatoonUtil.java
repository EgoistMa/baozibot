package com.shiropure.utils;

import java.util.HashMap;
import java.util.Objects;

public class SplatoonUtil {
    static HashMap<String, String> stage = new HashMap<String, String>() {
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
    static HashMap<String, String> rule = new HashMap<String, String>() {
        {
            put("Turf War", "占地对战");
            put("Tower Control", "真格塔楼");
            put("Rainmaker", "真格鱼虎对战");
            put("Splat Zones", "真格区域");
            put("Clam Blitz", "真格蛤蜊");
        }
    };
    static HashMap<String, String> weapons = new HashMap<String, String>() {
        {
            put("48d6e062dd8b7efb", "专业模型枪MG");
            put("3adcc67d997a5aa8", "碳纤维滚筒");
            put("61d373353a48eb2e", "爆炸泼桶");
            put("cfcd1cb4b09dc134", "喷射清洁枪");
            put("7813d8c4d9103b07", "洗笔桶");
            put("57684fd4ee281e09", "斯普拉滚筒");
            put("7a4705bd110b0cee", "满溢泡澡泼桶");
            put("ba2b27f7c17b1632", "三发猎鱼弓");
            put("3939e000515fe04c", "四重弹跳手枪 黑");
            put("6c5bbce4a6c63d0c", "开瓶喷泉枪");
            put("39e383c2f6d1ca3e", "鹦鹉螺号47");
            put("627d43fa3ab06066", "快速爆破枪");
            put("4dc28c8d9bca2dae", "斯普拉机动枪");
            put("670ae6f0c617cca4", "火热爆破枪");
            put("9e45d86ccd5fea1e", "圆珠笔");
            put("e2b5e6b340555596", "高压油管枪");
            put("59d665d32bb4122a", "窄域标记枪");
            put("77e4f6414e5a257d", "雨刷刮水刀");
            put("2c34cb021254c8f8", "顶尖射击枪");
            put("f668bd94e7ebf62d", "快速爆破枪 精英");
            put("d3d579406dbc0fc8", "专业模型枪MG");
            put("8c157e85b75798e7", "碳纤维滚筒");
            put("6529a2c7f83ca858", "爆炸泼桶");
            put("92f330252fdd9421", "喷射清洁枪");
            put("7d97fc215efe47fa", "洗笔桶");
            put("0637f4b4225f22b8", "斯普拉滚筒");
            put("13ef7ad9ba855fe1", "满溢泡澡泼桶");
            put("7ed9dd50b97d24f3", "三发猎鱼弓");
            put("8e59d00fc471083a", "四重弹跳手枪 黑");
            put("f09bb1bb306659dd", "开瓶喷泉枪");
            put("af505e7c4bdb7888", "鹦鹉螺号47");
            put("2d3900d357e005e9", "快速爆破枪");
            put("582d03e42a63596a", "斯普拉机动枪");
            put("9208eee9ecd2026e", "火热爆破枪");
            put("cb9126ea2928b619", "圆珠笔");
            put("70aa6b216ca8f01c", "高压油管枪");
            put("41492e011c163cc1", "窄域标记枪");
            put("df5b39ea3d32b25b", "雨刷刮水刀");
            put("617dedf1c26235dc", "顶尖射击枪");
            put("83275e416e7c1bc2", "快速爆破枪 精英");
            put("1f6ce9f852641707", "随机");
            put("d9bbd083353c118d", ".52加仑");
            put("9a9dcaa55c2f6545", "斯普拉射击枪");
            put("8c0617eafedab081", "14式竹筒枪·甲");
            put("0037f260dd45e397", "露营防空伞");
            put("cf6021b669c84379", "N-ZAP85");
            put("b5c29680486b0d1c", "巴勃罗");
            put("a99a6dd8efd4d5bb", "H3卷管枪");
            put("50563f3849b68e0a", "消防栓旋转枪");
            put("1e344559d62809b2", "溅镀枪");
            put("e594a5f9535eaf40", "北斋");
            put("7a2dd4e35809d537", "电动马达滚筒");
            put("1e9e39f56c9f6d1a", "鱿快洁α");
            put("5903a8cef02e4298", "开尔文525");
            put("1208a614c4bb22cf", "冲涂爆破枪");
            put("cb1243d9c1908a38", "斯普拉旋转枪");
            put("57717b83b81f474f", "公升4K");
            put("e70b1ea70b5916be", "广域标记枪");
            put("4b6949b9e979636c", "新星爆破枪");
            put("53cefbb18bb74cb3", "特工配件");
            put("1d1dd4a9165c4a0c", "斯普拉蓄力狙击枪");
            put("97745a7307013de3", "双重清洁枪");
            put("11ba9e5928b14318", "桶装旋转枪");
            put("5615e681937a3f12", "L3卷管枪");
            put("24cab1bbfb443770", "遮阳防空伞");
            put("ab50e9ae40810698", ".96加仑");
            put("319a3174ebcb8224", "新叶射击枪");
            put("a31def86914d98ae", "回旋泼桶");
            put("afc40370eb8a1aa5", "远距爆破枪");
            put("daa5ab5b571faec5", "可变式滚筒");
            put("8f8bfbac8a43c2c7", "工作刮水刀");
            put("70e60082b7f73c24", "LACT-450");
            put("13d0d18880c2f4e4", "飞溅泼桶");
            put("66e9cd75721a942f", "太空射击枪");
            put("7f8967b3ae112ffc", "宽滚筒");
            put("a1ea7028b1bcdd28", "R-PEN/5H");
        }
    };
    static HashMap<String, String> brands = new HashMap<String, String>() {
        {
            put("Splash Mob","寺门");
            put("Barazushi","散寿司");
            put("Emberz","七轮");
            put("SquidForce","战斗鱿鱼");
            put("Rockenberg","罗肯贝格");
            put("Zekko","泽酷");
            put("Forge","锻品");
            put("Firefin","暖流");
            put("Takoroka","暇古");
            put("Tentatek","艾洛眼");
            put("Inkline","时雨");
            put("Krak-On","海月");
            put("Skalop","帆立");
            put("Toni Kensa","剑尖鱿");
            put("Zink","钢铁先锋");
            put("Enperry","鱿皇");
            put("Annaki","无法无天");
        }
    };
    static HashMap<String, String> gears = new HashMap<String, String>() {
        {
            put("Classic Bowler","经典圆顶礼帽");
            put("White Shirt","白色衬衫");
            put("Red 3-Straps","魔鬼毡鞋 红色");
            put("Suede Basics","基地训练鞋 入门");
            put("Berry BlobMob Tee","密集水滴鱼上衣 莓果红");
            put("White Tee","鱿鱼T恤 白色");
            put("Moto Boots","越野摩托车靴");
            put("Double Egg Shades","铁蛋墨镜");
            put("Moto Shades","骑士偏光眼镜");
            put("Lo-Vis Visor","视野中空遮阳帽");
            put("Sailor-Stripe Tee","海军风横纹T恤");
            put("Splash Goggles","斯普拉护目镜");
            put("FishFry Biscuit Bandana","烧河豚丝巾 饼干");
            put("Force ReBoots","能力重置高筒鞋");
            put("Tan Retro Tee","再版T恤 棕色");
            put("Invisifloats","浮法玻璃透明眼镜");
            put("Ivory Peaks Tee","山岳T恤 象牙色");
            put("Snow Delta Straps","三角带运动凉鞋 雪白");
            put("Fancyfish Stitch","绣花衬衫 烟管鱼");
            put("Duskwave Tee","日落渐层T恤");
            put("Cephalo Pods","骨传导鳍挂式耳机EP");
            put("Hickory Work Cap","山核桃条纹工作帽");
            put("Squidlife Headphones","立体声录音机耳机");
            put("Pearl 01STERs","01STER 琥珀");
            put("BlobMob Mask","水滴鱼微笑面罩");
            put("Button-Clown Shirt","小丑衬衫");
            put("BlobMob Flip-Flops","BS拖鞋");
            put("White Layered LS","两件式长袖上衣 白色");
            put("Umibozu Home Jersey","海坊主坦克背心 主场");
            put("Red Hi-Horses","高筒海马鞋 红色");
            put("Classic Straw Boater","经典鱿鱼草帽");
            put("Squid-Stitch Slip-Ons","懒人鞋 千鸟");
            put("Squidmark Sweat","鱿鱼交叉套头衫 芥末黄");
            put("Seahorse Shoreliner","海马古巴衬衫");
            put("Choco Clogs","鳄鱼鞋 巧克力");
            put("Red & Black Squidkid IV","饵木钓鞋4 红配黑");
            put("Half-Rim Glasses","半框眼镜");
            put("Arctic Duck Boots","猎鸭靴 白雪");
            put("Tri-Squid Tee","鱿鱼先生T恤");
            put("Zombie Hi-Horses","高筒海马鞋 僵尸");
            put("Orange Arrows","箭标鞋 橘色");
            put("Squidbeak Shield","黑鸢防护面罩R255");
            put("Kensa Coat","剑尖教练外套");
            put("Yellow Layered LS","两件式长袖上衣 芥末黄");
            put("Squid Satin Jacket","鱿鱼飞行外套");
            put("Wharfside Cap","瓜皮帽");
            put("White V-Neck Tee","鱿鱼V领T恤 白色");
            put("Barazushi Black Tee","散寿司T恤 糙米");
            put("Suede Bosses","基地训练鞋 高手");
            put("Annaki Flannel Hoodie","无法无天法兰绒连帽上衣");
            put("Hula Punk Shirt","彼得朋克衬衫");
            put("Mako Bucket Hi-Tops","双鱼翅");
            put("Arrow Pull-Ons","箭标鞋 剑尖鱿特制");
            put("Red FishFry Sandals","烧河豚浴室拖鞋 红色");
            put("Squidvader Cap","墨行帽");
            put("Red Battlecrab Shell","长腿护甲 红色");
            put("Blue & Black Squidkid IV","饵木钓鞋4 蓝配黑");
            put("Rugby King 08","王者橄榄球衫008");
            put("Sunset Orca Hi-Tops","虎鲸高筒鞋 日落");
            put("Rockenberg Black","罗肯贝格T恤 黑色");
            put("Rugby King 10","王者橄榄球衫010");
            put("Cycling Shirt","单车衫");
            put("Desert Chukkas","查卡靴 三明治");
            put("18K Aviators","明星墨镜 18K");
            put("Rockenberg White","罗肯贝格T恤 白色");
            put("Pink Dadfoot Sandals","非常非常拖鞋 洋红色");
            put("Hunter Hi-Tops","高筒帆布鞋 黄麻");
            put("Woolly Urchins Classic","羊毛海胆经典帽");
            put("B-ball Jersey (Home)","篮球运动服 主场");
            put("Tinted Shades","有色眼镜");
            put("Zekko Baseball LS","泽酷插肩袖上衣");
            put("Blue Lo-Tops","低筒鞋 蓝色");
            put("Wasabi Tabi","NNJ运动鞋 绿色");
            put("Cyan Trainers","糖果运动鞋 蓝绿色");
            put("Choco Layered LS","两件式长袖上衣 巧克力色");
            put("Black Tee","鱿鱼T恤 黑色");
            put("Squidband","乒乓头带");
            put("Purple Hi-Horses","高筒海马鞋 紫色");
            put("Party Hard Hat","号角安全帽BF");
            put("Black Flip-Flops","BB拖鞋");
            put("Firefin Facemask","暖流口罩");
            put("Black 8-Bit FishFry","烧河豚点阵T恤 黑色");
            put("Tan Work Boots","磨砂皮革靴 黄色");
            put("Face Visor","脸部中空遮阳帽");
            put("Custom Trail Boots","登山鞋 订制");
            put("Brain Strainer","灵光一闪安全帽");
            put("Zapfish Satin Jacket","鲶鱼飞行外套");
            put("Tennis Headband","网球头带");
            put("Oyster Clogs","鳄鱼鞋 牡蛎");
            put("Gray Vector Tee","向量图案T恤 灰色");
            put("Barazushi Sakura Tee","散寿司T恤 红豆饭");
            put("Takoroka Visor","暇古中空遮阳帽");
            put("Takoroka Nylon Vintage","暇古尼龙拉链上衣 怀旧");
            put("White 8-Bit FishFry","烧河豚点阵T恤 白色");
            put("White Headband","头带 白色");
            put("Full-Moon Glasses","玳瑁圆形眼镜");
            put("Red Hi-Tops","高筒帆布鞋 番茄");
            put("Bamboo Hat","斗笠");
            put("Olive Ski Jacket","登山外套 橄榄绿");
            put("Jean Dream Bucket","模板喷画牛仔布帽");
            put("White 3-Straps","魔鬼毡鞋 白色");
            put("Pink Trainers","糖果运动鞋 粉红色");
            put("Annaki Mask","无法无天口罩");
            put("Gray Hoodie","帆立连帽上衣 灰色");
            put("Dark-Roast Boaties","帆船鞋 深咖啡色");
            put("Streetstyle Cap","饮料标志帽");
            put("Barazushi Rice Tee","散寿司T恤 白米");
            put("Urchins Cap","海胆BB帽");
            put("Hockey Helmet","伊卡洛斯曲棍球安全帽");
            put("Ink-Tinted Goggles","桶眼鱼护目镜");
            put("Patchwork Bomber","鱼片外套");
            put("Pineapple Ringer","滚边T恤 凤梨");
            put("Cyan Dadfoot Sandals","非常非常拖鞋 绿蓝色");
            put("Tri-Shred Tee","鱿鱼丝T恤 白色");
            put("Winkle Stripe Helm","贝壳安全帽");
            put("Annaki Charms","无法无天方形眼镜");
            put("Annaki Habaneros","无法无天厚底鞋 哈瓦那辣椒");
            put("Logo Aloha Shirt","很多Logo夏威夷衬衫");
            put("Bucket Hat","鱿鱼渔夫帽");
            put("Crimson Parashooter","降落伞上衣 石榴红");
            put("Matcha Down Jacket","轻量羽绒外套 抹茶色");
            put("Fashion Splash Shirt","喷墨衬衫");
            put("Zink Layered LS","钢铁先锋两件式长袖上衣");
            put("Green Tee","绿色T恤");
            put("Airflow & Hustle Jacket","永远凉爽外套");
            put("Studio Headphones","工作室耳机");
            put("Manatee Swag Sweat","海月海牛套头衫");
            put("FishFry Visor","烧河豚中空遮阳帽");
            put("Knitted Hat","针织帽");
            put("E-JECT 30XX","MODZ-9");
            put("Black Squideye","鱿鱼眼T恤 黑色");
            put("Hunting Boots","狩猎靴");
            put("Lumberjack Shirt","秋季法兰绒衬衫");
            put("Yellow FishFry Sandals","烧河豚浴室拖鞋 黄色");
            put("Orca Bolero","短版小外套 负片");
            put("Forge Mask","锻品口罩");
            put("Pilot Goggles","飞行员护目镜");
            put("Business Animal","鱼片衬衫 野外观察");
            put("Khaki Ranger Vest","狩猎背心KK");
            put("Takoroka Mesh","暇古网帽");
            put("Chili Octo Aloha","扁面蛸夏威夷衬衫");
            put("Slamgerine Slip-Ons","贝壳柱WO");
            put("Arctic Monster Parka","怪物 PCU56");
            put("Trail Boots","登山鞋 轻量");
            put("Blue Retro Tee","再版T恤 蓝色");
            put("Apex Sweater","3D针织衫 鲨鱼");
            put("Urban Upcycle Top","鱼片衬衫 城市");
            put("Eelzebub Tee","加百列T恤");
            put("Orange Dadfoot Sandals","非常非常拖鞋 橘色");
            put("Annaki Choker Tee","无法无天T恤配颈链");
            put("Arrow Toesies Blu","箭标拖鞋 蓝黄色");
            put("Black Inky Rider","鱿鱼骑士皮外套 黑色");
            put("Swim Goggles","竞技泳镜");
            put("Ink-Black Paddle Jack","划桨外套 负片");
            put("Sudadera Roja","巴哈连帽上衣 红色");
            put("Ocho OctoPhones","耳朵章鱼8");
            put("Ink-Black Flap Cap","遮阳帽 墨黑");
            put("Home-Team Catcher","捕手面罩FU");
            put("Turbo Tabi Red","MOVE跑鞋 红色");
            put("Tenya OctoReds","天亚8篮球鞋 红色");
            put("Tri-Octo Tee","章鱼先生T恤");
            put("Black Polo","乒乓球Polo衫");
            put("Designer Headphones","极光耳机");
            put("Aloha Shirt","舞动鱿鱼夏威夷衬衫");
            put("Octobowler Shirt","章鱼保龄球衫");
            put("Astro Helm","飞行员安全帽");
            put("Inkfall Shirt","墨汁喷洒衬衫");
            put("Octosquid Tandem Tee","依古T恤 五分袖");
            put("Apple Ringer","滚边T恤 苹果");
            put("Short Beanie","短款圆顶毛帽");
            put("Blue Peaks Tee","山岳T恤 蓝色");
            put("Dark Bomber Jacket","轰炸机飞行外套 负片");
            put("Sky-Blue Squideye","鱿鱼眼T恤 浅蓝色");
            put("Barazushi Anorak","散寿司派克大衣");
            put("Annaki Beret","无法无天别针贝雷帽");
            put("Annaki Tigers","无法无天厚底鞋 爵士经典");
            put("Chocolate Dakroniks","贝壳鞋 巧克力");
            put("Firewave Tee","火热渐层T恤");
            put("Cycling Cap","单车帽");
            put("Octo Tackle Helmet Deco","装饰奖章鱼安全帽");
        }
    };
    static HashMap<String, String> powers = new HashMap<String, String>() {
        {

            put("Ink Saver (Main)","提升墨汁效率（主要武器）");
            put("Comeback","回归");
            put("Ninja Squid","鱿鱼忍者");
            put("Swim Speed Up","提升鱿鱼冲刺速度");
            put("Stealth Jump","隐身跳跃");
            put("Quick Super Jump","缩短超级跳跃时间");
            put("Run Speed Up","提升人类移动速度");
            put("Drop Roller","受身术");
            put("Special Power Up","提升特殊武器性能");
            put("Unknown","？");
            put("Respawn Punisher","提升复活惩罚");
            put("Sub Resistance Up","减轻次要武器影响");
            put("Ink Resistance Up","减轻对手墨汁影响");
            put("Special Saver","降低特殊武器减少量");
            put("Quick Respawn","缩短复活时间");
            put("Intensify Action","行动强化");
            put("Ink Recovery Up","提升墨汁回复力");
            put("Special Charge Up","提升特殊武器增加量");
            put("Haunt","复仇");
            put("Opening Gambit","最初冲刺");
            put("Sub Power Up","提升次要武器性能");
            put("Object Shredder","提升对物体攻击力");
            put("Ink Saver (Sub)","提升墨汁效率（次要武器）");
            put("Thermal Ink","热力墨汁");
            put("Tenacity","逆境强化");
            put("Last-Ditch Effort","最后冲刺");
        }
    };
    public static String translatePower(String eng)
    {
        return powers.getOrDefault(eng,eng);
    }
    public static String translateBrand(String eng)
    {
        return brands.getOrDefault(eng,eng);
    }
    public static String translateGear(String eng)
    {
        return gears.getOrDefault(eng,eng);
    }
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
