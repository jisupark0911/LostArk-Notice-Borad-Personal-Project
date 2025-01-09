package com.example.LostArkNoticeBoard.service;

import com.example.LostArkNoticeBoard.dto.freeBoardForm;
import com.example.LostArkNoticeBoard.entity.FreeBoard;
import com.example.LostArkNoticeBoard.repository.FreeBoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FreeBoardApiService {

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    public List<FreeBoard> freeBoardIndex() {
        return freeBoardRepository.findAll();
    }

    public FreeBoard freeBoardShow(Long id) {
        return freeBoardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
    }

    public FreeBoard freeBoardCreate(freeBoardForm dto, String userEmail, String userName) {

        FreeBoard freeBoard = dto.freeEntity();

        if (freeBoard.getId() != null) {
            return null;
        }

        freeBoard.setUserEmail(userEmail);
        freeBoard.setUsername(userName);

        return freeBoardRepository.save(freeBoard);
    }


    public FreeBoard freeBoardUpdate(Long id, freeBoardForm dto, String userEmail, String userName) {

        FreeBoard freeBoard = dto.freeEntity();

        log.info("id: {}, freeBoard: {}", id, freeBoard.toString());

        FreeBoard freeBoardTarget = freeBoardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        freeBoard.setUserEmail(userEmail);
        freeBoard.setUsername(userName);

        freeBoardTarget.freeBoardPatch(freeBoard);

        FreeBoard freeBoardUpdated = freeBoardRepository.save(freeBoardTarget);

        return freeBoardUpdated;
    }


    public FreeBoard freeBoardDelete(Long id) {
        FreeBoard freeBoardTarget = freeBoardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        if(freeBoardTarget == null){
            return null;
        }
        freeBoardRepository.delete(freeBoardTarget);
        return freeBoardTarget;
    }

    public Page<FreeBoard> getFreeBoardList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return freeBoardRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Page<FreeBoard> getFreeBoardListByKeyword(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        return freeBoardRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
    }


}
