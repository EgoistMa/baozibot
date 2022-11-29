package com.shiropure.api;

import com.shiropure.utils.IOUtil;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplatoonApi {
    static String api = "https://splatoon3.ink/data/schedules.json";
    public static Map<String, Object> SplatoonSchedules(String mode) throws IOException {
        Map<String, Object> dataMap = (Map<String, Object>) IOUtil.sendAndGetResponseMap(new URL(api), "GET", null, null).get("data");
        Map<String, Object> bankaraSchedulesMap = (Map<String, Object>)dataMap.get(mode);
        List<Map<String, Object>> Schedules = (List<Map<String, Object>>) bankaraSchedulesMap.get("nodes");
        return readNode(Schedules);
    }
    private static Map<String, Object> readNode(List<Map<String, Object>> Schedules) throws IOException {
        if (Schedules == null || Schedules.isEmpty()) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> node : Schedules) {
            String startTime = (String) node.get("startTime");
            int matchNumber=0;
            List<Map<String, Object>> MatchSettingsMap = (List<Map<String, Object>>) node.get("bankaraMatchSettings");
            for(Map<String, Object> match : MatchSettingsMap)
            {
                List<Map<String, Object>> vsStages = (List<Map<String, Object>>) match.get("vsStages");
                    for(Map<String, Object> vsStage : vsStages)
                    {
                        map.put(startTime + "matchNumber:"+ ++matchNumber,vsStage.get("name"));
                    }
            }
        }
        return map;
    }
}
