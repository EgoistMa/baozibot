package com.shiropure.api;

import com.shiropure.utils.IOUtil;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplatoonApi {
    static String api = "https://splatoon3.ink/data/schedules.json";
    public static Map<String, Object> SplatoonSchedules() throws IOException {
        String grt = IOUtil.sendAndGetResponseString(new URL(api), "GET", null, null);
        Map<String, Object> dataMap = (Map<String, Object>) IOUtil.sendAndGetResponseMap(new URL(api), "GET", null, null).get("data");
        Map<String, Object> bankaraSchedulesMap = (Map<String, Object>)dataMap.get("bankaraSchedules");
        List<Map<String, Object>> bankaraSchedules = (List<Map<String, Object>>) bankaraSchedulesMap.get("nodes");
        return readNode(bankaraSchedules);
    }
    //todo 修改Map的格式 和 获取的内容 ，顺序
    private static Map<String, Object> readNode(List<Map<String, Object>> bankaraSchedules) throws IOException {
        if (bankaraSchedules == null || bankaraSchedules.isEmpty()) {
            return null;
        }
        int counter= 1;
        Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> node : bankaraSchedules) {
            List<Map<String, Object>> bankaraMatchSettingsMap = (List<Map<String, Object>>) node.get("bankaraMatchSettings");
            for(Map<String, Object> match : bankaraMatchSettingsMap)
            {
                List<Map<String, Object>> matchStages = (List<Map<String, Object>>) match.get("vsStages");
                    for(Map<String, Object> matchStage : matchStages)
                    {
                        map.put("" + counter++,matchStage.get("name"));
                    }
            }
        }
        return map;
    }
}
