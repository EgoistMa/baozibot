package com.shiropure.api;

import com.shiropure.Model.Schedules.Schedules;
import com.shiropure.Model.Schedules.SplatoonSchedules;
import com.shiropure.Model.Schedules.Stage;
import com.shiropure.Model.Schedules.Weapon;
import com.shiropure.utils.DateUtil;
import com.shiropure.utils.SplatoonApiUtil;

import java.io.*;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.shiropure.utils.IOUtil.*;

public class SplatoonSchedulesApi {
    static final String schedulesApi = "https://splatoon3.ink/data/schedules.json";
    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    /**
     * 提取并对象化根据 schedulesApi 获取的json文件
     *
     * @return SplatoonSchedules 类
     */
    public static SplatoonSchedules SplatoonSchedules() throws IOException
    {
        OffsetDateTime currentTime = OffsetDateTime.now();
        String filePath = "./Caches/schedules/"+currentTime.getHour()+".data";
        Map<String, Object> dataMap = getCacheOrDownloadfromApi(filePath,schedulesApi);

        Schedules[] regularSchedules = readRegularSchedules(dataMap);
        Schedules[] bankaraSchedules = readBankaraSchedules(dataMap);
        Schedules[] xSchedules = readXSchedules(dataMap);
        Schedules[] leagueSchedules = readLeagueSchedules(dataMap);
        Schedules[] coopGroupingSchedule = readCoopSchedules(dataMap);
        return new SplatoonSchedules(regularSchedules,bankaraSchedules,xSchedules,leagueSchedules,coopGroupingSchedule);
    }
    /**
     * 根据获取的Map 进一步解析出关于合作模式的日程计划
     *
     * @param dataMap  合作模式的Map<String,Object>
     * @return 返回场次列表
     */
    public static Schedules[] readCoopSchedules(Map<String, Object> dataMap)
    {
        LinkedList<Schedules>schedules = new LinkedList<>();
        Map<String, Object> leagueSchedules = SplatoonApiUtil.openNode(dataMap,"coopGroupingSchedule");
        Map<String, Object> regularSchedules = SplatoonApiUtil.openNode(leagueSchedules,"regularSchedules");
        List<Map<String, Object>> nodes = SplatoonApiUtil.openNodeList(regularSchedules,"nodes");
        for(Map<String, Object> node : nodes)
        {
            OffsetDateTime startTime = DateUtil.dateFormatter(node.get("startTime").toString(),dateTimeFormatter);
            OffsetDateTime endTime = DateUtil.dateFormatter(node.get("endTime").toString(),dateTimeFormatter);
            Map<String, Object> setting = SplatoonApiUtil.openNode(node,"setting");
            Map<String, Object> coopStage = SplatoonApiUtil.openNode(setting,"coopStage");
            LinkedList<Stage> stages = new LinkedList<>();
            String stageName = coopStage.get("name").toString();
            Map<String, Object> image = SplatoonApiUtil.openNode(coopStage,"image");
            String stageUrl = image.get("url").toString();
            stages.addLast(new Stage(stageName,stageUrl));
            //获取武器
            LinkedList<Weapon> weapons = new LinkedList<>();
            List<Map<String, Object>> weaponsNode = SplatoonApiUtil.openNodeList(setting,"weapons");
            for(Map<String, Object> weapon :weaponsNode)
            {
                String weaponName = weapon.get("name").toString();
                Map<String, Object> weaponImage = SplatoonApiUtil.openNode(weapon,"image");
                String weaponUrl = weaponImage.get("url").toString();
                weapons.addLast(new Weapon(weaponName,weaponUrl));
            }
            schedules.addLast(new Schedules(startTime,endTime,stages.toArray(new Stage[0]),weapons.toArray(new Weapon[0])));
        }
        return schedules.toArray(new Schedules[0]);
    }
    /**
     * 根据获取的Map 进一步解析出关于组排模式的日程计划
     *
     * @param dataMap  组排模式的Map<String,Object>
     * @return 返回场次列表
     */
    public static Schedules[] readLeagueSchedules (Map<String, Object> dataMap)
    {
        LinkedList<Schedules>schedules = new LinkedList<>();
        Map<String, Object> leagueSchedules = SplatoonApiUtil.openNode(dataMap,"leagueSchedules");
        List<Map<String, Object>> nodes = SplatoonApiUtil.openNodeList(leagueSchedules,"nodes");
        for(Map<String, Object> node : nodes)
        {
            OffsetDateTime startTime = DateUtil.dateFormatter(node.get("startTime").toString(),dateTimeFormatter);
            OffsetDateTime endTime = DateUtil.dateFormatter(node.get("endTime").toString(),dateTimeFormatter);
            Map<String, Object> leagueMatchSetting = SplatoonApiUtil.openNode(node,"leagueMatchSetting");
            List<Map<String, Object>> vsStages = SplatoonApiUtil.openNodeList(leagueMatchSetting,"vsStages");
            LinkedList<Stage> stages = new LinkedList<>();
            for(Map<String, Object> stage : vsStages)
            {
                String stageName = stage.get("name").toString();
                Map<String, Object> image = SplatoonApiUtil.openNode(stage,"image");
                String stageUrl = image.get("url").toString();
                stages.addLast(new Stage(stageName,stageUrl));
            }
            Map<String, Object> vsRule = SplatoonApiUtil.openNode(leagueMatchSetting,"vsRule");
            String rule = vsRule.get("name").toString();
            schedules.addLast(new Schedules(startTime,endTime,stages.toArray(new Stage[0]),rule));

        }
        return schedules.toArray(new Schedules[0]);
    }
    /**
     * 根据获取的Map 进一步解析出关于X模式的日程计划
     *
     * @param dataMap  X模式的Map<String,Object>
     * @return 返回场次列表
     */
    public static Schedules[] readXSchedules (Map<String, Object> dataMap)
    {
        LinkedList<Schedules>schedules = new LinkedList<>();
        Map<String, Object> xSchedules = SplatoonApiUtil.openNode(dataMap,"xSchedules");
        List<Map<String, Object>> nodes = SplatoonApiUtil.openNodeList(xSchedules,"nodes");
        for(Map<String, Object> node : nodes)
        {
            OffsetDateTime startTime = DateUtil.dateFormatter(node.get("startTime").toString(),dateTimeFormatter);
            OffsetDateTime endTime = DateUtil.dateFormatter(node.get("endTime").toString(),dateTimeFormatter);
            Map<String, Object> xMatchSetting = SplatoonApiUtil.openNode(node,"xMatchSetting");
            List<Map<String, Object>> vsStages = SplatoonApiUtil.openNodeList(xMatchSetting,"vsStages");
            LinkedList<Stage> stages = new LinkedList<>();
            for(Map<String, Object> stage : vsStages)
            {
                String stageName = stage.get("name").toString();
                Map<String, Object> image = SplatoonApiUtil.openNode(stage,"image");
                String stageUrl = image.get("url").toString();
                stages.addLast(new Stage(stageName,stageUrl));
            }
            Map<String, Object> vsRule = SplatoonApiUtil.openNode(xMatchSetting,"vsRule");
            String rule = vsRule.get("name").toString();
            schedules.addLast(new Schedules(startTime,endTime,stages.toArray(new Stage[0]),rule));
        }
        return schedules.toArray(new Schedules[0]);
    }
    /**
     * 根据获取的Map 进一步解析出关于真格模式的日程计划
     *
     * @param dataMap  真格模式的Map<String,Object>
     * @return 返回场次列表
     */
    public static Schedules[] readBankaraSchedules (Map<String, Object> dataMap)
    {
        LinkedList<Schedules>schedules = new LinkedList<>();
        Map<String, Object> bankaraSchedules = SplatoonApiUtil.openNode(dataMap,"bankaraSchedules");
        List<Map<String, Object>> nodes = SplatoonApiUtil.openNodeList(bankaraSchedules,"nodes");
        for(Map<String, Object> node : nodes)
        {
            OffsetDateTime startTime = DateUtil.dateFormatter(node.get("startTime").toString(),dateTimeFormatter);
            OffsetDateTime endTime = DateUtil.dateFormatter(node.get("endTime").toString(),dateTimeFormatter);
            List<Map<String, Object>> regularMatchSetting = SplatoonApiUtil.openNodeList(node,"bankaraMatchSettings");
            for(Map<String, Object> setting:regularMatchSetting)
            {
                List<Map<String, Object>> vsStages = SplatoonApiUtil.openNodeList(setting,"vsStages");
                LinkedList<Stage> stages = new LinkedList<>();
                for(Map<String, Object> stage : vsStages)
                {
                    String stageName = stage.get("name").toString();
                    Map<String, Object> image = SplatoonApiUtil.openNode(stage,"image");
                    String stageUrl = image.get("url").toString();
                    stages.addLast(new Stage(stageName,stageUrl));
                }
                Map<String, Object> vsRule = SplatoonApiUtil.openNode(setting,"vsRule");
                String rule = vsRule.get("name").toString();
                String mode = setting.get("mode").toString();
                schedules.addLast(new Schedules(startTime,endTime,stages.toArray(new Stage[0]),rule,mode));
            }
        }
        return schedules.toArray(new Schedules[0]);
    }
    /**
     * 根据获取的Map 进一步解析出关于涂地模式的日程计划
     *
     * @param dataMap  涂地模式的Map<String,Object>
     * @return 返回场次列表
     */
    public static Schedules[] readRegularSchedules (Map<String, Object> dataMap)
    {
        LinkedList<Schedules>schedules = new LinkedList<>();
        Map<String, Object> regularSchedules = SplatoonApiUtil.openNode(dataMap,"regularSchedules");
        List<Map<String, Object>> nodes = SplatoonApiUtil.openNodeList(regularSchedules,"nodes");
        for(Map<String, Object> node : nodes)
        {
            OffsetDateTime startTime = DateUtil.dateFormatter(node.get("startTime").toString(),dateTimeFormatter);
            OffsetDateTime endTime = DateUtil.dateFormatter(node.get("endTime").toString(),dateTimeFormatter);
            Map<String, Object> regularMatchSetting = SplatoonApiUtil.openNode(node,"regularMatchSetting");
            List<Map<String, Object>> vsStages = SplatoonApiUtil.openNodeList(regularMatchSetting,"vsStages");
            LinkedList<Stage> stages = new LinkedList<>();
            for(Map<String, Object> stage : vsStages)
            {
                String stageName = stage.get("name").toString();
                Map<String, Object> image = SplatoonApiUtil.openNode(stage,"image");
                String stageUrl = image.get("url").toString();
                stages.addLast(new Stage(stageName,stageUrl));
            }
            Map<String, Object> vsRule = SplatoonApiUtil.openNode(regularMatchSetting,"vsRule");
            String rule = vsRule.get("name").toString();
            schedules.addLast(new Schedules(startTime,endTime,stages.toArray(new Stage[0]),rule));
        }
        return schedules.toArray(new Schedules[0]);
    }
}
