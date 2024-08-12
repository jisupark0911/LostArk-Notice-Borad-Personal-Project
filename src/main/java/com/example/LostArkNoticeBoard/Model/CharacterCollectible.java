package com.example.LostArkNoticeBoard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CharacterCollectible {
    @JsonProperty("Type")
    private String type;

    @JsonProperty("Icon")
    private String icon;

    @JsonProperty("Point")
    private int point;

    @JsonProperty("MaxPoint")
    private int maxPoint;

    @JsonProperty("CollectiblePoints")
    private List<CollectiblePoint> collectiblePoint;

    @Getter
    @Setter
    public static class CollectiblePoint{
        @JsonProperty("PointName")
        private String pointName;

        @JsonProperty("Point")
        private int point;

        @JsonProperty("MaxPoint")
        private int maxPoint;
    }
}
