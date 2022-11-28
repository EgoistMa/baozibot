package com.shiropure.cron;

import com.shiropure.config.RobotConfig;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;

public class RobotCronJob implements Job {
    @Deprecated
    public volatile static Timer service = new Timer();
    public volatile static CopyOnWriteArrayList<Runnable> tasks = new CopyOnWriteArrayList<>();
    @Override
    public void execute(JobExecutionContext context) {
        try {
            if (!RobotConfig.enableRobot) {
                return;
            }
            RobotConfig.logger.info(new Date() + ", run robot cron job...");
            for (Runnable task : tasks) {
                task.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
