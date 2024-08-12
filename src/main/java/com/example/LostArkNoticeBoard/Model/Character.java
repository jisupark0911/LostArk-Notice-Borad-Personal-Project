package com.example.LostArkNoticeBoard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Character {
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


/*
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
}