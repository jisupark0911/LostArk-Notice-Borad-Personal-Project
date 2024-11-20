package com.example.LostArkNoticeBoard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Event {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Thumbnail")
    private String thumbnail;

    @JsonProperty("Link")
    private String link;

    @JsonProperty("StartDate")
    private String startDate;

    @JsonProperty("EndDate")
    private String endDate;

    @JsonProperty("RewardDate")
    private String rewardDate;

}
