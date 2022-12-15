package com.shiropure.handler.intercept.impl;

import com.shiropure.config.RobotConfig;
import com.shiropure.handler.intercept.intercept;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;

@intercept
public class BaseBeforeInterceptor extends AdaptInterceptor {


    /**
     * 返回真则拦截该事件
     * @param event 消息事件
     * @return 是否要拦截
     */
    @Override
    public boolean interceptBefore(MessageEvent event) {
        // 如果如何，放行开机命令
        if (getContent(event).equals(RobotConfig.commandPrefix + "开机") && getSenderId(event).equals("332883417")) {
            RobotConfig.enableRobot = true;

            return false;
        }
        if (getContent(event).equals(RobotConfig.commandPrefix + "关机") && getSenderId(event).equals("332883417")) {
            RobotConfig.enableRobot = false;
            return true;
        }
        System.out.println("RobotConfig.enableRobot = " + RobotConfig.enableRobot);
        if (!RobotConfig.enableRobot)               return true;
        if (!(event instanceof GroupMessageEvent))  return false;
        String gid = String.valueOf(((GroupMessageEvent) event).getGroup().getId());
        if (RobotConfig.include.isEmpty()) {
            return RobotConfig.exclude.contains(gid);
        }
        return !RobotConfig.include.contains(gid);
    }
}
