package com.example.LostArkNoticeBoard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor//기본생성자 자동추가
@ToString
@Getter
@Entity

public class FreeBoard {

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

/*
    public FreeBoard(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "FreeBoard{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
*/

}
