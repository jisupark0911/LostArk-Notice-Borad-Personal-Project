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

/*
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRewardDate() {
        return rewardDate;
    }

    public void setRewardDate(String rewardDate) {
        this.rewardDate = rewardDate;
    }
 */
}
