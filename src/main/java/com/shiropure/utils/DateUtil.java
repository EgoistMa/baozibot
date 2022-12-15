package com.shiropure.utils;


import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String getReadableChinaTime(OffsetDateTime time)
    {
        return getReadableTime(getChinaTime(time));
    }
    public static String getReadableTime(OffsetDateTime time)
    {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM'月'dd'日' HH'时'");
        return time.format(pattern);
    }
    public static OffsetDateTime getChinaTime(OffsetDateTime time)
    {
        return time.plusHours(8);
    }
    public static OffsetDateTime dateFormatter(String DateTimeStr,DateTimeFormatter pattern)
    {
        ZoneId zoneId = ZoneId.of("UTC");
        LocalDateTime dateTime = LocalDateTime.parse(DateTimeStr, pattern);
        ZoneOffset offset = zoneId.getRules().getOffset(dateTime);
        return OffsetDateTime.of(dateTime, offset);
    }
    public static String HHformater(int str)
    {
        return str<10? "0"+str : ""+str;
    }
}
