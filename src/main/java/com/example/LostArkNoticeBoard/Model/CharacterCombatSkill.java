package com.example.LostArkNoticeBoard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterCombatSkill {
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Icon")
    private String icon;

    @JsonProperty("Level")
    private int level;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("SkillType")
    private int skillType;

    @JsonProperty("Tripods")
    private List<SkillTripod> tripods;

    @JsonProperty("Rune")
    private SkillRune rune;

    @JsonProperty("Tooltip")
    private String tooltip;



    @Getter
    @Setter
    public static class SkillTripod {
        @JsonProperty("Tier")
        private int tier;

        @JsonProperty("Slot")
        private int slot;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Icon")
        private String icon;

        @JsonProperty("Level")
        private int level;

        @JsonProperty("IsSelected")
        private boolean isSelected;

        @JsonProperty("Tooltip")
        private String tooltip;
    }

    @Getter
    @Setter
    public static class SkillRune {
        @JsonProperty("Name")
        private String name;

        @JsonProperty("Icon")
        private String icon;

        @JsonProperty("Grade")
        private String grade;

        @JsonProperty("Tooltip")
        private String tooltip;
    }
}
