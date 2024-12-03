package com.example.LostArkNoticeBoard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Guardian {

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
class RewardItems {

    @JsonProperty("ItemLevel")
    private int itemLevel;

    @JsonProperty("Items")
    private List<RewardItem> items;
}

@Getter
@Setter
class RewardItem {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Icon")
    private String icon;

    @JsonProperty("Grade")
    private String grade;

    @JsonProperty("StartTimes")
    private List<LocalDateTime> startTimes;
}


