package com.example.LostArkNoticeBoard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class JobBoard {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;
/*
    public String getId() {
        return id;
    }
    */

}
