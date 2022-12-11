package com.shiropure.Model.Schedules;

public class Stage {
    public String stageName;
    public String stageUrl;

    public Stage(String stageName, String stageUrl) {
        this.stageName = stageName;
        this.stageUrl = stageUrl;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getStageUrl() {
        return stageUrl;
    }

    public void setStageUrl(String stageUrl) {
        this.stageUrl = stageUrl;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "stageName='" + stageName + '\'' +
                ", stageUrl='" + stageUrl + '\'' +
                '}';
    }
}
