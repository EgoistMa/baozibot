package com.shiropure.starter;

import com.shiropure.config.ConfigManager;
import com.shiropure.config.RobotConfig;
import com.shiropure.proxy.MessageHandlerProxy;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class BaoziBotStarter {
    private static MessageHandlerProxy messageHandler;
    public static void Start(JavaPlugin plugin) throws Exception {
        //  初始化
        try {
            initRobotConfig(plugin);
        } catch (IllegalAccessException | IOException e){
            e.printStackTrace();
        }
        // 消息代理
        messageHandler = new MessageHandlerProxy();
        // todo 后台主程序
        RobotCronJob.cron();
    }

    public synchronized static void initRobotConfig(JavaPlugin plugin) throws IllegalAccessException,IOException {
        Yaml yaml = new Yaml();

        if (plugin != null) {
            RobotConfig.logger = plugin.getLogger();
            RobotConfig.configFolder = plugin.getConfigFolder();
            RobotConfig.dataFolder = plugin.getDataFolder();
        }

        File file  = new File(RobotConfig.configFolder + "/" + RobotConfig.mainConfigPathName);
        Class<RobotConfig> c = RobotConfig.class;
        Field[] fields = c.getDeclaredFields();
        if (file.exists()) {
            RobotConfig.logger.info("正在初始化机器人配置");
            Map<?, ?> map = null;
            try (FileInputStream in = new FileInputStream(file)) {
                map = yaml.loadAs(in, Map.class);
            }
            // 反射设置 RobotConfig
            if (map != null) {
                for (Field field : fields) {
                    if (map.containsKey(field.getName())) {
                        try {
                            if (map.get(field.getName()) != null)
                                field.set(null, map.get(field.getName()));
                        } catch (Exception ignored) {
                            // 可能是名字类型不符合，忽略
                        }
                    }
                }
            }
            RobotConfig.logger.info("配置初始化完成");
        }
            else {
                RobotConfig.logger.info("未检测到配置文件，创建配置文件模板");
                boolean newFile = file.createNewFile();
                try (FileOutputStream fileOutputStream = new FileOutputStream(file, false)) {
                    String template = ConfigManager.TEMPLATE;
                    fileOutputStream.write(template.getBytes(StandardCharsets.UTF_8));
                    RobotConfig.logger.info("成功创建配置文件初始模板，重启机器人生效");
//                System.exit(100);
                } catch (Exception e) {
                    e.printStackTrace();
                    RobotConfig.logger.info("配置文件填充错误，请手动配置");
                }
            }
        }
    }
