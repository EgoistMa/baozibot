package com.shiropure.handler.impl;

import com.shiropure.config.RobotConfig;
import com.shiropure.proxy.Context;
import com.shiropure.handler.handler;
import com.shiropure.utils.OfUtil;
import com.shiropure.utils.StringUtil;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.*;

/**
 * 俄罗斯轮盘赌
 * @author Happysnaker
 * @description
 * @date 2022/7/9
 * @email happysnaker@foxmail.com
 */
@handler
@SuppressWarnings("unchecked")
public class RussianRouletteMessageEventHandler extends GroupMessageEventHandler {
    public final String reload = "装弹";
    public final String shot = "开枪";
    public final String stop = "卸下弹夹";

    /**
     * 帮忙者
     */
    static class Helper {
        // 总弹数
        public int totalNum = 6;
        // 会打死人的弹数
        public int realNum = 1;
        // 奖励的基准，如果中弹的概率为 p，不中弹的概率为 1 - p，那么中弹会扣除 base * (1 - p) 的积分，而不中弹会增加 base * p 的积分
        public int base = 100;
        // 已经被命中的成员
        public Set<String> shotMan = new HashSet<>();
        // 成员获取的收益
        public Map<String, Integer> income = new HashMap<>();

        public Helper(int totalNum, int realNum) {
            this.totalNum = totalNum;
            this.realNum = realNum;
        }

        public void updateIncome(String qq, int delta) {
            income.put(qq, income.getOrDefault(qq, 0) + delta);
            income.get(qq);
        }

        public Helper() {
        }

        public int getRewardOrPunishment(boolean shot, String qq) {
            // 被击中的概率
            double p = realNum * 1.0 / totalNum;
            Random random = new Random();
            int v = base + (int) (!shot ? base * p : base * (1 - p));
            int r = random.nextInt(base / 10);
            v = random.nextInt(2) == 0 ? v + r : v - r;
            return shot ? v + income.getOrDefault(qq, 0) : v;
        }

        public boolean shot() {
            int random = (int) (Math.random() * totalNum + 1);
            return random <= realNum;
        }
    }

    private final Map<String, Helper> map = new HashMap<>();

    @Override
    public List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx) {
        return null;
    }


    @Override
    public boolean shouldHandle(MessageEvent event, Context ctx) {
        return startWithKeywords(event, OfUtil.ofSet(reload, shot, stop));
    }
}
