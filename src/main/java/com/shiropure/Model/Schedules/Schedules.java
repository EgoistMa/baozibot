package com.shiropure.Model.Schedules;

import java.time.OffsetDateTime;
import java.util.Arrays;

public class Schedules {
    public OffsetDateTime startTime; //开始时间
    public OffsetDateTime endTime;  //结束时间
    public Stage[] stages;  //地图 + url
    public String rule;//比如 真格区域 ，可为无
    public String mode;// 比赛模式  比如：开放 挑战，可为无

    public Weapon[] weapons;//比赛中用的武器

    public Schedules(OffsetDateTime startTime, OffsetDateTime endTime, Stage[] stages, String rule) //regularSchedules,XSchedules,leagueSchedules可用
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.stages = stages;
        this.rule = rule;
    }

    public Schedules(OffsetDateTime startTime, OffsetDateTime endTime, Stage[] stages, String rule, String mode)//bankaraSchedules 可用
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.stages = stages;
        this.rule = rule;
        this.mode = mode;
    }

    public Schedules(OffsetDateTime startTime, OffsetDateTime endTime, Stage[] stages, Weapon[] weapons) //coopGroupingSchedule可用
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.stages = stages;
        this.weapons = weapons;
    }
    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
    }

    public OffsetDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(OffsetDateTime endTime) {
        this.endTime = endTime;
    }

    public Stage[] getStages() {
        return stages;
    }

    public void setStages(Stage[] stages) {
        this.stages = stages;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Weapon[] getWeapons() {
        return weapons;
    }

    public void setWeapons(Weapon[] weapons) {
        this.weapons = weapons;
    }

    @Override
    public String toString() {
        return "Schedules{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", stages=" + Arrays.toString(stages) +
                ", rule='" + rule + '\'' +
                ", mode='" + mode + '\'' +
                ", weapons=" + Arrays.toString(weapons) +
                '}';
    }
}
