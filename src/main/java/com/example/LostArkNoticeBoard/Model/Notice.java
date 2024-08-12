package com.example.LostArkNoticeBoard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notice {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Date")
    private String date;

    @JsonProperty("Link")
    private String link;

    @JsonProperty("Type")
    private String type;

/*
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
 */
}

