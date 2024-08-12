package com.example.LostArkNoticeBoard.dto;

import com.example.LostArkNoticeBoard.entity.JobBoard;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //기본생성자 자동추가
@ToString
public class jobBoardForm {
    private Long id;
    private String title;
    private String content;

    public JobBoard jobEntity(){
        return new JobBoard(id,title,content);
    }


}
