package com.shiropure.config;

import com.shiropure.cron.RobotCronJob;
import com.shiropure.utils.IOUtil;
import com.shiropure.utils.StringUtil;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.TimerTask;

import static com.shiropure.utils.RobotUtil.getContent;
import static com.shiropure.utils.RobotUtil.getSenderId;

public class ConfigManager {
    public static void recordFailLog(MessageEvent event, String errorMsg) {
        RobotCronJob.service.schedule(new TimerTask() {
            @Override
            public void run() {
                String filePath = ConfigManager.getDataFilePath("error.log");
                try {
                    IOUtil.writeToFile(new File(filePath), formatLog(event) + "\n错误日志：" + errorMsg + "\n\n");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }, 0);
    }
    public static String formatLog(MessageEvent event) {
        if (event == null) return "[" + StringUtil.formatTime() + "]";
        String content = getContent(event);
        String sender = getSenderId(event);
        if (!(event instanceof GroupMessageEvent)) {
            return "[sender:" + sender + "-" + StringUtil.formatTime() + "] -> " + content;
        }
        long groupId = ((GroupMessageEvent) event).getGroup().getId();
        return "[sender:" + sender + " - group:" + groupId + " - " + StringUtil.formatTime() + "] -> " + content;
    }
    public static String getDataFilePath(String fileName) {
        return RobotConfig.dataFolder + "/" + fileName;
    }
}
