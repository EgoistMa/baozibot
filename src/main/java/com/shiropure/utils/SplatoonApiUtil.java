package com.shiropure.utils;

import java.util.List;
import java.util.Map;

public class SplatoonApiUtil {
    public static Map<String, Object> openNode(Map<String, Object> node,String nextNodeName)
    {
        return (Map<String, Object>)(node.get(nextNodeName));
    }
    public static List<Map<String, Object>> openNodeList(Map<String, Object> node, String nextNodeName)
    {
        return (List<Map<String, Object>>) node.get(nextNodeName);
    }
}
