package com.example.LostArkNoticeBoard.dto;

import com.example.LostArkNoticeBoard.entity.FreeBoardComment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class freeBoardCommentDto {
    private Long id;
    @JsonProperty("freeBoardId")
    private Long freeBoardId;
    private String nickname;
    private String body;

    public static freeBoardCommentDto createfreeBoardCommentDto(FreeBoardComment comment) {
        return new freeBoardCommentDto(
                comment.getId(),
                comment.getFreeBoard().getId(),
                comment.getNickname(),
                comment.getBody()
        );

    }
}
