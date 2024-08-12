package com.example.LostArkNoticeBoard.dto;

import com.example.LostArkNoticeBoard.entity.FreeBoard;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class freeBoardForm {
    private Long id; //생성할떄는 자동생성이지만 수정할떄는 어떤아이디인지 알려줘야해서 필요함
    private String title;
    private String content;

/*

    public freeBoardForm(String title, String content){ //전송받은 제목과 내용을 저장하는 생성자
        this.title = title;
        this.content = content;
    }


    @Override
    public String toString() {
        return "freeBoardForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
*/

    public FreeBoard freeEntity() { // 전달받은 객체를 엔티티에 반환
        return new FreeBoard(id, title, content);
    }


}
