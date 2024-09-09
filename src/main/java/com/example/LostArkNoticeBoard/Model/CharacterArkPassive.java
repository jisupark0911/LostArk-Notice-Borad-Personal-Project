package com.example.LostArkNoticeBoard.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterArkPassive {

    @JsonProperty("IsArkPassive")
    private boolean isArkPassive;

    @JsonProperty("Points")
    private List<ArkPassivePoint> arkPassivePoint;

    @JsonProperty("Effects")
    private List<ArkPassiveEffectSkill> arkPassiveEffectSkill;

    @Getter
    @Setter
    public static class ArkPassivePoint{
        @JsonProperty("Name")
        private String name;

        @JsonProperty("Value")
        private int value;

        @JsonProperty("Tooltip")
        private String tooltip;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class ArkPassiveEffectSkill{
        @JsonProperty("Name")
        private String name;

        @JsonProperty("Description")
        private String description;

        @JsonProperty("Icon")
        private String icon;

        @JsonProperty("Tooltip") //툴팁 속성값 없음
        private String tooltip;
    }
}
