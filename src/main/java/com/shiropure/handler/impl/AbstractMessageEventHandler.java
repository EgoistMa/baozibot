package com.shiropure.handler.impl;

import com.shiropure.config.RobotConfig;
import com.shiropure.handler.MessageEventHandler;
import com.shiropure.proxy.Context;
import com.shiropure.utils.RobotUtil;
import com.shiropure.utils.StringUtil;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.utils.MiraiLogger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.shiropure.config.ConfigManager.recordFailLog;

public abstract class AbstractMessageEventHandler extends RobotUtil implements MessageEventHandler, Serializable {


    /**
     * 当前机器人的 qq 号，注意可能有多个 qq（多个机器人）
     *
     * @deprecated 这个值可能是不准确的，需调用 {@link Bot#getInstances()} 方法获取机器人列表
     */
    @Deprecated
    public List<String> qqList = null;


    public static MiraiLogger logger = RobotConfig.logger;


    /**
     * 记录 info 日志，输出至控制台
     *
     * @param msg 输出内容
     */
    public void info(String msg) {
        logger.info(msg);
    }

    /**
     * 记录 debug 日志，输出至控制台
     *
     * @param msg 输出内容
     */
    public void debug(String msg) {
        logger.debug(msg);
    }


    /**
     * 记录 error 日志，输出至控制台
     *
     * @param msg 输出内容
     */
    public void error(String msg) {
        logger.error(msg);
    }


    /**
     * 记录 error 日志，输出至错误文件
     *
     * @param msg 输出内容
     */
    public void logError(MessageEvent event, String msg) {
        recordFailLog(event, msg);
    }


    /**
     * 记录 error 日志，输出至错误文件
     */
    public void logError(MessageEvent event, Throwable e) {
        recordFailLog(event, StringUtil.getErrorInfoFromException(e));
    }


    /**
     * 读取运行时机器人的 QQ，并初始化 qqs，此方法需动态调用，因为一开始用户可能未登录
     */
    @Deprecated
    public void initBotQQ() {
        List<Bot> bots = Bot.getInstances();
        List<String> qqs = new ArrayList<>();
        for (Bot bot : bots) {
            qqs.add(String.valueOf(bot.getId()));
        }
        this.qqList = qqs;
    }

    /**
     * 提取纯文本消息，消息将不会包含图片、表情等任何非文字
     *
     * @param event 消息时间
     * @return 纯文本
     */
    public String getPlantContent(MessageEvent event) {
        return getOnlyPlainContent(event);
    }

    /**
     * 检查事件消息是否以关键字开头
     *
     * @param event 传入的事件
     * @param keywords 是否包含集合关键字
     * @return 此事件是否以集合内的关键字开始
     */
    public boolean startWithKeywords(MessageEvent event, Collection<String> keywords) {
        String content = getPlantContent(event);
        if (content != null) {
            for (String keyword : keywords) {
                if (content.startsWith(keyword)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将要回复的消息，子类需要实现
     */
    public abstract List<MessageChain> handleMessageEvent(MessageEvent event, Context ctx);
}

