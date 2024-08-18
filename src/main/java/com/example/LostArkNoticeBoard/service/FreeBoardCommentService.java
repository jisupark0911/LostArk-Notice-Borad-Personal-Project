package com.example.LostArkNoticeBoard.service;

import com.example.LostArkNoticeBoard.dto.freeBoardCommentDto;
import com.example.LostArkNoticeBoard.entity.FreeBoard;
import com.example.LostArkNoticeBoard.entity.FreeBoardComment;
import com.example.LostArkNoticeBoard.repository.FreeBoardCommentRepository;
import com.example.LostArkNoticeBoard.repository.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FreeBoardCommentService {
    @Autowired
    private FreeBoardCommentRepository freeBoardCommentRepository;

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    public List<freeBoardCommentDto> freeBoardComments(Long freeBoardId){

        return freeBoardCommentRepository.findByFreeBoardId(freeBoardId)
                .stream()
                .map(freeBoardComment -> freeBoardCommentDto.createfreeBoardCommentDto(freeBoardComment))
                .collect(Collectors.toList());
        }

        @Transactional
    public freeBoardCommentDto freeBoardCreate(Long freeBoardId, freeBoardCommentDto dto) {
        FreeBoard freeBoard = freeBoardRepository.findById(freeBoardId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!" + "대상 게시글이 없습니다."));
        FreeBoardComment freeBoardComment = FreeBoardComment.freeBoardCreateComment(dto,freeBoard);
        FreeBoardComment freeBoardCreated = freeBoardCommentRepository.save(freeBoardComment);

        return freeBoardCommentDto.createfreeBoardCommentDto(freeBoardCreated);
    }

    @Transactional
    public freeBoardCommentDto freeBoardUpdate(Long id, freeBoardCommentDto dto) {
        FreeBoardComment freeBoardTarget = freeBoardCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" + "대상 댓글이 없습니다."));
        freeBoardTarget.freeBoardPatch(dto);
        FreeBoardComment freeBoardUpdated = freeBoardCommentRepository.save(freeBoardTarget);

        return freeBoardCommentDto.createfreeBoardCommentDto(freeBoardUpdated);
    }

    @Transactional
    public freeBoardCommentDto freeBoardDelete(Long id) {
        FreeBoardComment freeBoardTarget = freeBoardCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패!" + "대상이 없습니다."));
        freeBoardCommentRepository.delete(freeBoardTarget);

        return freeBoardCommentDto.createfreeBoardCommentDto(freeBoardTarget);
    }
}


