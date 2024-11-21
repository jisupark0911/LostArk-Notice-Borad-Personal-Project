package com.example.LostArkNoticeBoard.dto;

import com.example.LostArkNoticeBoard.entity.FreeBoard;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class freeBoardForm {
    private Long id; //생성할떄는 자동생성이지만 수정할떄는 어떤아이디인지 알려줘야해서 필요함
    private String title;
    private String content;
    private String userName;
    private String userEmail;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private Long viewCount = 0L;
    private Long likeCount = 0L;

    public FreeBoard freeEntity() { // 전달받은 객체를 엔티티에 반환
        return new FreeBoard(id, title, content,userName,userEmail,createdAt,viewCount,likeCount);
    }


}
