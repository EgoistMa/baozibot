package com.shiropure.utils;

public class DateUtil {
    public static String getDateRegion(String dateString)
    {
        //sample input          2022-11-29T13:18:23Z
        //sample region  "UTC:   2022-11-29T12:00:00Z"

        String dateAndTime[] = dateString.split("T");
        String time = dateAndTime[1].split(":")[0];
        //todo 如果减去8小时为前一天 需要将日期提前一天并重新计算时间
        int currentTime = (Integer.parseInt(time) - 8);

        String out = "";
        if(currentTime % 2==0)
        {
            String StringCurrentTime = currentTime+"";
            if(StringCurrentTime.length() == 1)
            {
                StringCurrentTime = "0" + StringCurrentTime;
            }
            out += dateAndTime[0] + "T" + StringCurrentTime +":00:00Z";
        }else {
            currentTime --;
            String StringCurrentTime = currentTime+"";
            if(StringCurrentTime.length() == 1)
            {
                StringCurrentTime = "0" + StringCurrentTime;
            }
            out += dateAndTime[0] + "T" + StringCurrentTime +":00:00Z";
        }
        return out;
    }
    public static String getNextDateRegion(String dateString)
    {
        String dateAndTime[] = dateString.split("T");
        String time = dateAndTime[1].split(":")[0];
        int currentTime = Integer.parseInt(time) + 2;
        String stringCurrentTime = currentTime+"";
        if(stringCurrentTime.length() == 1)
        {
            stringCurrentTime = "0"+stringCurrentTime;
        }


        String out = "";
        out += dateAndTime[0] + "T" + stringCurrentTime +":00:00Z";
        return out;
    }
}
