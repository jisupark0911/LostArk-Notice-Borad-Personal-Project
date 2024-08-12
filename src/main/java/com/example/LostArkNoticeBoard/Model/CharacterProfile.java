package com.example.LostArkNoticeBoard.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
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

    @JsonProperty("ArkPassive")
    private ArkPassive arkPassive;

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

    /* getter, setter 어노테이션쓰면 필요없음

    public String getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(String characterImage) {
        this.characterImage = characterImage;
    }

    public int getExpeditionLevel() {
        return expeditionLevel;
    }

    public void setExpeditionLevel(int expeditionLevel) {
        this.expeditionLevel = expeditionLevel;
    }

    public String getPvpGradeName() {
        return pvpGradeName;
    }

    public void setPvpGradeName(String pvpGradeName) {
        this.pvpGradeName = pvpGradeName;
    }

    public int getTownLevel() {
        return townLevel;
    }

    public void setTownLevel(int townLevel) {
        this.townLevel = townLevel;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGuildMemberGrade() {
        return guildMemberGrade;
    }

    public void setGuildMemberGrade(String guildMemberGrade) {
        this.guildMemberGrade = guildMemberGrade;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public int getUsingSkillPoint() {
        return usingSkillPoint;
    }

    public void setUsingSkillPoint(int usingSkillPoint) {
        this.usingSkillPoint = usingSkillPoint;
    }

    public int getTotalSkillPoint() {
        return totalSkillPoint;
    }

    public void setTotalSkillPoint(int totalSkillPoint) {
        this.totalSkillPoint = totalSkillPoint;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public List<Tendency> getTendencies() {
        return tendencies;
    }

    public void setTendencies(List<Tendency> tendencies) {
        this.tendencies = tendencies;
    }

    public ArkPassive getArkPassive() {
        return arkPassive;
    }

    public void setArkPassive(ArkPassive arkPassive) {
        this.arkPassive = arkPassive;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getCharacterLevel() {
        return characterLevel;
    }

    public void setCharacterLevel(int characterLevel) {
        this.characterLevel = characterLevel;
    }

    public String getCharacterClassName() {
        return characterClassName;
    }

    public void setCharacterClassName(String characterClassName) {
        this.characterClassName = characterClassName;
    }

    public String getItemAvgLevel() {
        return itemAvgLevel;
    }

    public void setItemAvgLevel(String itemAvgLevel) {
        this.itemAvgLevel = itemAvgLevel;
    }

    public String getItemMaxLevel() {
        return itemMaxLevel;
    }

    public void setItemMaxLevel(String itemMaxLevel) {
        this.itemMaxLevel = itemMaxLevel;
    }

   */

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

    @Setter
    @Getter
    public static class ArkPassive {
        @JsonProperty("IsArkPassive")
        private boolean isArkPassive;

        @JsonProperty("Points")
        private List<ArkPassivePoint> points;
    }

    @Setter
    @Getter
    public static class ArkPassivePoint {
        @JsonProperty("Name")
        private String name;

        @JsonProperty("Value")
        private int value;

        @JsonProperty("Tooltip")
        private String tooltip;
    }

}
