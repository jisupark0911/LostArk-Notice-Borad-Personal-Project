package com.example.LostArkNoticeBoard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

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

public void setDate(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    this.date = sdf.format(date);
}

    public String getDate() {
        return date;
    }
}

