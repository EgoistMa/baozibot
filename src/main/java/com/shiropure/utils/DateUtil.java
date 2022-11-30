package com.shiropure.utils;


import com.shiropure.config.RobotConfig;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String getTimeRegion(OffsetDateTime offsetDateTime, int offset)
    {
        offsetDateTime = offsetDateTime.plusSeconds(offset);
        offsetDateTime = roundingToHour(offsetDateTime);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        if (offsetDateTime.getHour() % 2 != 0) {
            offsetDateTime = offsetDateTime.minusHours(1);
        }
        return fmt.format(offsetDateTime);
    }
    public static OffsetDateTime roundingToHour(OffsetDateTime offsetDateTime)
    {
        int minuets = offsetDateTime.getMinute();
        int second = offsetDateTime.getSecond();
        OffsetDateTime result = offsetDateTime.minusMinutes(minuets);
        result = result.minusSeconds(second);
        return result;
    }
}
