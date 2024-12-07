package com.example.LostArkNoticeBoard.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Guardian {

    @JsonProperty("Raids")
    private List<GuardianRaid> raids;

    @JsonProperty("RewardItems")
    private List<LevelRewardItems> rewardItems;

    @Getter
    @Setter
    public static class GuardianRaid {
        @JsonProperty("Name")
        private String name;

        @JsonProperty("Description")
        private String description;

        @JsonProperty("MinCharacterLevel")
        private int minCharacterLevel;

        @JsonProperty("MinItemLevel")
        private int minItemLevel;

        @JsonProperty("StartTime")
        private String startTime;

        @JsonProperty("EndTime")
        private String endTime;

        @JsonProperty("Image")
        private String image;
    }

    @Getter
    @Setter
    public static class LevelRewardItems {
        @JsonProperty("ItemLevel")
        private int itemLevel;

        @JsonProperty("Items")
        private List<RewardItem> items;
    }

    @Getter
    @Setter
    public static class RewardItem {
        @JsonProperty("Name")
        private String name;

        @JsonProperty("Icon")
        private String icon;

        @JsonProperty("Grade")
        private String grade;

        @JsonProperty("StartTimes")
        private List<String> startTimes; // 날짜와 시간 정보
    }

    @Override
    public String toString() {
        return "Guardian{" +
                "raids=" + raids +
                ", rewardItems=" + rewardItems +
                '}';
    }
}