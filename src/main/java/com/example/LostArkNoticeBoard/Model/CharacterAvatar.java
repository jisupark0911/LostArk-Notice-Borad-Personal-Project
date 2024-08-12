package com.example.LostArkNoticeBoard.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterAvatar {

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Icon")
    private String icon;

    @JsonProperty("Grade")
    private String grade;

    @JsonProperty("IsSet")
    private boolean isSet;

    @JsonProperty("IsInner")
    private boolean isInner;

    @JsonProperty("Tooltip")
    private String tooltip;
/*
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean getIsSet() {
        return isSet;
    }

    public void setIsSet(boolean isSet) {
        this.isSet = isSet;
    }

    public boolean getIsInner() {
        return isInner;
    }

    public void setIsInner(boolean isInner) {
        this.isInner = isInner;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }
*/
}
