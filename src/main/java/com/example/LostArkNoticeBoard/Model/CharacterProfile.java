package com.example.LostArkNoticeBoard.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true) //길드이름과 길드멤버 속성값이 없음
@Getter
@Setter
public class CharacterProfile {

    @JsonProperty("CharacterImage")
    private String characterImage;

    @JsonProperty("ExpeditionLevel")
    private int expeditionLevel;

    @JsonProperty("PvpGradeName")
    private String pvpGradeName;

    @JsonProperty("TownLevel")
    private int townLevel;

    @JsonProperty("TownName")
    private String townName;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("GuildMemberGrade")
    private String guildMemberGrade;

    @JsonProperty("GuildName")
    private String guildName;

    @JsonProperty("UsingSkillPoint")
    private int usingSkillPoint;

    @JsonProperty("TotalSkillPoint")
    private int totalSkillPoint;

    @JsonProperty("Stats")
    private List<Stat> stats;

    @JsonProperty("Tendencies")
    private List<Tendency> tendencies;

    @JsonProperty("ServerName")
    private String serverName;

    @JsonProperty("CharacterName")
    private String characterName;

    @JsonProperty("CharacterLevel")
    private int characterLevel;

    @JsonProperty("CharacterClassName")
    private String characterClassName;

    @JsonProperty("ItemAvgLevel")
    private String itemAvgLevel;

    @JsonProperty("ItemMaxLevel")
    private String itemMaxLevel;

    public String getTitle() {
        return title != null ? title : "-"; //칭호 설정을 안해서 null일때가 있음
    }
    public String getGuildName() {
        return guildName != null ? guildName : "-";
    }



    @Setter
    @Getter
    public static class Stat {
        @JsonProperty("Type")
        private String type;

        @JsonProperty("Value")
        private String value;

        @JsonProperty("Tooltip")
        private List<String> tooltip;
    }

    @Setter
    @Getter
    public static class Tendency {
        @JsonProperty("Type")
        private String type;

        @JsonProperty("Point")
        private int point;

        @JsonProperty("MaxPoint")
        private int maxPoint;
    }



}
