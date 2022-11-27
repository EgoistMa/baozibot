package com.shiropure.config;

import net.mamoe.mirai.utils.MiraiLogger;

import java.io.File;

public class RobotConfig {
    /**
     * 插件版本，每次更新请更新此配置
     */
    public static final String CURRENT_VERSION ="0.1.0";

    /**
     * 主配置文件名
     */
    public static String mainConfigPathName = "config.yaml";

    /**
     * 配置文件存放的目录路径
     */
    public static File configFolder;

    /**
     * 日志
     */
    public volatile static MiraiLogger logger;

    /**
     * 数据存放的目录路径
     */
    public static File dataFolder;

    public volatile static boolean enableRobot = true;

    // ------------ 用户配置 ----------------

}
