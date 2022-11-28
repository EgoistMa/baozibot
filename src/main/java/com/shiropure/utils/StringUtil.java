package com.shiropure.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StringUtil {
    public static String formatTime() {
        return formatTime(System.currentTimeMillis());
    }
    public static String getErrorInfoFromException(Throwable e) {
        try {
            StringWriter sw = null;
            PrintWriter pw = null;
            try {
                sw = new StringWriter();
                pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                return sw.toString();
            } finally {
                assert sw != null;
                assert pw != null;
                sw.close();
                pw.close();
            }
        } catch (Exception e1) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public static String formatTime(long ts) {
        Date d = new Date(ts);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }
    public static List<String> splitSpaces(String s) {
        List<String> ans = new ArrayList<>();
        for (String s1 : s.split("\\s+")) {
            if (!s1.isEmpty()) {
                ans.add(s1);
            }
        }
        return ans;
    }
}
