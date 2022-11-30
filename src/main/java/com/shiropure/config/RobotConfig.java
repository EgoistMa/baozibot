package com.shiropure.config;

import net.mamoe.mirai.utils.MiraiLogger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RobotConfig {
    public static File dataFolder = new File("./data/");
    public static Boolean enableRobot = true;
    public volatile static MiraiLogger logger;
    public static String commandPrefix = "，";
    public volatile static boolean enableAt = true;
    public static List<String> include = new ArrayList<>();
    public static List<String> exclude = new ArrayList<>();
    public static int timeout = 3000;
}
