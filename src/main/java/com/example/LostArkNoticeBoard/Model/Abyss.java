package com.example.LostArkNoticeBoard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter


public class Abyss {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("MinCharacterLevel")
    private int minCharacterLevel;

    @JsonProperty("MinItemLevel")
    private String minItemLevel;

    @JsonProperty("AreaName")
    private String areaName;

    @JsonProperty("StartTime")
    private String startTime;

    @JsonProperty("EndTime")
    private String endTime;

    @JsonProperty("Image")
    private String image;

    @JsonProperty("RewardItems")
    private List<RewardItem> rewardItem;


    @Getter
    @Setter
    private static class RewardItem{

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Icon")
        private String icon;

        @JsonProperty("Grade")
        private String grade;

        @JsonProperty("StartTimes")
        private List<LocalDateTime> startTimes;

    }

}
