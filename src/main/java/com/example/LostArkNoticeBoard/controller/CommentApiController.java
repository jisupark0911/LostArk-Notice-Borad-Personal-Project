package com.example.LostArkNoticeBoard.controller;

import com.example.LostArkNoticeBoard.dto.freeBoardCommentDto;
import com.example.LostArkNoticeBoard.service.FreeBoardCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private FreeBoardCommentService freeBoardCommentService;

    @GetMapping("/community/freeBoard/{freeBoardId}/comments")
    public ResponseEntity<List<freeBoardCommentDto>> freeBoardComments (@PathVariable Long freeBoardId){
        List<freeBoardCommentDto> dtos = freeBoardCommentService.freeBoardComments(freeBoardId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/community/freeBoard/{freeBoardId}/comments")
    public ResponseEntity<freeBoardCommentDto> freeBoardCreate (@PathVariable Long freeBoardId, @RequestBody freeBoardCommentDto dto){
        freeBoardCommentDto freeBoardCreateDto = freeBoardCommentService.freeBoardCreate(freeBoardId, dto);

        return ResponseEntity.status(HttpStatus.OK).body(freeBoardCreateDto);
    }

    @PatchMapping("/community/freeBoard/{id}")
    public ResponseEntity<freeBoardCommentDto> freeBoardUpdate (@PathVariable Long id, @RequestBody freeBoardCommentDto dto){
        freeBoardCommentDto freeBoardUpdatedDto = freeBoardCommentService.freeBoardUpdate(id,dto);

        return ResponseEntity.status(HttpStatus.OK).body(freeBoardUpdatedDto);
    }

    @DeleteMapping("/community/freeBoard/comments/{id}")
    public ResponseEntity<freeBoardCommentDto> freeBoardDelete(@PathVariable Long id){
        freeBoardCommentDto freeBoardDeleteDto = freeBoardCommentService.freeBoardDelete(id);

        return ResponseEntity.status(HttpStatus.OK).body(freeBoardDeleteDto);
    }
}
