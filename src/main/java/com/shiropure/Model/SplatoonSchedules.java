package com.shiropure.Model;


import java.util.Arrays;

public class SplatoonSchedules {
    public Schedules[] regularSchedules;
    public Schedules[] bankaraSchedules;
    public Schedules[] xSchedules;
    public Schedules[] leagueSchedules;
    public Schedules[] coopGroupingSchedule;

    public SplatoonSchedules(Schedules[] regularSchedules, Schedules[] bankaraSchedules, Schedules[] xSchedules, Schedules[] leagueSchedules, Schedules[] coopGroupingSchedule) {
        this.regularSchedules = regularSchedules;
        this.bankaraSchedules = bankaraSchedules;
        this.xSchedules = xSchedules;
        this.leagueSchedules = leagueSchedules;
        this.coopGroupingSchedule = coopGroupingSchedule;
    }

    @Override
    public String toString() {
        return "SplatoonSchedules{" +
                "regularSchedules=" + Arrays.toString(regularSchedules) +
                ", bankaraSchedules=" + Arrays.toString(bankaraSchedules) +
                ", xSchedules=" + Arrays.toString(xSchedules) +
                ", leagueSchedules=" + Arrays.toString(leagueSchedules) +
                ", coopGroupingSchedule=" + Arrays.toString(coopGroupingSchedule) +
                '}';
    }
}
