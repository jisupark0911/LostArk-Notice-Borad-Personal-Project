package com.example.LostArkNoticeBoard.entity;

import com.example.LostArkNoticeBoard.dto.freeBoardCommentDto;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "freeboardcomment")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class FreeBoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 다대일
    @JoinColumn(name="free_board_id")
    private FreeBoard freeBoard; //부모 게시글

    @Column
    private String nickname;

    @Column
    private String body;

    public static FreeBoardComment freeBoardCreateComment(freeBoardCommentDto dto, FreeBoard freeBoard) {

        if(dto.getId() != null){
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        }
        if(dto.getFreeBoardId() != freeBoard.getId()){
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        }
        return new FreeBoardComment(
                null, //dto.getId();
                freeBoard,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void freeBoardPatch(freeBoardCommentDto dto) {
        if(this.id != dto.getId()){
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");
        }
        if(dto.getNickname() != null){
            this.nickname = dto.getNickname();
        }
        if(dto.getBody() != null){
            this.body = dto.getBody();
        }
    }
}
