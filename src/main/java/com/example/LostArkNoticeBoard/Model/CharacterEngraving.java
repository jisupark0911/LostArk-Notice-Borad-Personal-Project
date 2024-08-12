package com.example.LostArkNoticeBoard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterEngraving {
    @JsonProperty("Engravings")
    private List<Engraving> engraving;

    @JsonProperty("Effects")
    private List<EngravingEffect> engravingEffect;

    @JsonProperty("ArkPassiveEffects")
    private List<ArkPassiveEffect> arkPassiveEffect;

    @Getter
    @Setter
    public static class Engraving{
        @JsonProperty("Slot")
        private int slot;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Icon")
        private String icon;

        @JsonProperty("Tooltip")
        private String tooltip;

    }

    @Getter
    @Setter
    public static class EngravingEffect{
        @JsonProperty("Icon")
        private String icon;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Description")
        private String description;
    }

    @Getter
    @Setter
    public static class ArkPassiveEffect{
        @JsonProperty("AbilityStoneLevel")
        private int abilityStoneLevel;

        @JsonProperty("Grade")
        private String grade;

        @JsonProperty("Level")
        private int level;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Description")
        private String description;
    }
}
