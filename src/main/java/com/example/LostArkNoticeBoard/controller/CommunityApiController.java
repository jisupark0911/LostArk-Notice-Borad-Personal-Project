package com.example.LostArkNoticeBoard.controller;

import com.example.LostArkNoticeBoard.dto.freeBoardForm;
import com.example.LostArkNoticeBoard.dto.freeBoardCommentDto;
import com.example.LostArkNoticeBoard.entity.FreeBoard;
import com.example.LostArkNoticeBoard.entity.FreeBoardLike;
import com.example.LostArkNoticeBoard.repository.FreeBoardRepository;
import com.example.LostArkNoticeBoard.repository.FreeBoardLikeRepository;
import com.example.LostArkNoticeBoard.service.FreeBoardApiService;
import com.example.LostArkNoticeBoard.service.FreeBoardCommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/community/freeBoard")
public class CommunityApiController {

    @Autowired
    private FreeBoardApiService freeBoardApiService;
    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Autowired
    private FreeBoardCommentService freeBoardCommentService;

    @Autowired
    private FreeBoardLikeRepository freeBoardLikeRepository;

    @Autowired
    private HttpSession session;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getFreeBoardList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(value = "keyword", required = false) String keyword) {

        Page<FreeBoard> freeBoardPage;

        if (keyword == null || keyword.isEmpty()) {
            freeBoardPage = freeBoardApiService.getFreeBoardList(page, size);
        } else {
            freeBoardPage = freeBoardApiService.getFreeBoardListByKeyword(page, size, keyword);
        }

        int nextPage = page + 1;
        int previousPage = page - 1;

        Map<String, Object> response = new HashMap<>();
        response.put("freeBoardList", freeBoardPage.getContent());
        response.put("totalPages", freeBoardPage.getTotalPages());
        response.put("currentPage", page);
        response.put("nextPage", nextPage);
        response.put("previousPage", previousPage);
        response.put("totalElements", freeBoardPage.getTotalElements());

        List<Map<String, Object>> pageLinks = new ArrayList<>();
        for (int i = 0; i < freeBoardPage.getTotalPages(); i++) {
            Map<String, Object> pageLink = new HashMap<>();
            pageLink.put("pageNumber", i);
            pageLink.put("displayPageNumber", i + 1);
            pageLink.put("isActive", i == page);
            pageLinks.add(pageLink);
        }
        response.put("pageLinks", pageLinks);
        response.put("hasPrevious", freeBoardPage.hasPrevious());
        response.put("hasNext", freeBoardPage.hasNext());

        if (keyword != null && !keyword.isEmpty()) {
            response.put("freeBoardKeyword", keyword);
        } else {
            response.put("freeBoardKeyword", "");
        }

        return ResponseEntity.ok(response);
    }




    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> getFreeBoardDetails(@PathVariable Long id, HttpSession session) {
        log.info("id = " + id);

        FreeBoard freeBoardEntity = freeBoardRepository.findById(id).orElse(null);

        if (freeBoardEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "게시글을 찾을 수 없습니다."));
        }

        freeBoardEntity.setViewCount(freeBoardEntity.getViewCount() + 1);
        freeBoardRepository.save(freeBoardEntity);

        String userEmail = (String) session.getAttribute("loginEmail");
        boolean isAuthor = false;
        boolean isLiked = false;

        if (userEmail != null && userEmail.equals(freeBoardEntity.getUserEmail())) {
            isAuthor = true;
        }

        if (userEmail != null) {
            Optional<FreeBoardLike> existingLike = freeBoardLikeRepository.findByUserEmailAndFreeBoardId(userEmail, id);
            isLiked = existingLike.isPresent();
        }

        List<freeBoardCommentDto> freeBoardCommentDtos = freeBoardCommentService.freeBoardComments(id);

        Map<String, Object> response = new HashMap<>();
        response.put("id", freeBoardEntity.getId());
        response.put("title", freeBoardEntity.getTitle());
        response.put("content", freeBoardEntity.getContent());
        response.put("username", freeBoardEntity.getUsername());
        response.put("userEmail", freeBoardEntity.getUserEmail());
        response.put("freeBoard", freeBoardEntity);
        response.put("freeBoardCommentDtos", freeBoardCommentDtos);
        response.put("isAuthor", isAuthor);
        response.put("isLiked", isLiked);
        response.put("viewCount", freeBoardEntity.getViewCount());
        response.put("likeCount", freeBoardEntity.getLikeCount());
        response.put("createdAt", freeBoardEntity.getCreatedAt());

        response.put("loginEmail", userEmail);

        return ResponseEntity.ok(response);
    }



    @PostMapping
    public ResponseEntity<FreeBoard> freeBoardCreate(@RequestBody freeBoardForm dto, HttpSession session) {
        String userEmail = (String) session.getAttribute("loginEmail");
        String userName = (String) session.getAttribute("userName");

        FreeBoard freeBoardCreated = freeBoardApiService.freeBoardCreate(dto, userEmail, userName);

        return (freeBoardCreated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(freeBoardCreated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }




    @PatchMapping("/{id}")
    public ResponseEntity<FreeBoard> freeBoardUpdate(@PathVariable Long id, @RequestBody freeBoardForm dto, HttpSession session) {
        String userEmail = (String) session.getAttribute("loginEmail");
        String userName = (String) session.getAttribute("userName");

        FreeBoard freeBoardUpdated = freeBoardApiService.freeBoardUpdate(id, dto, userEmail, userName);

        return (freeBoardUpdated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(freeBoardUpdated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FreeBoard> freeBoardDelete(@PathVariable Long id,HttpSession session) {
        FreeBoard freeBoardDeleted = freeBoardApiService.freeBoardDelete(id);
        return (freeBoardDeleted != null) ?
               ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
               ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/{id}/toggleLike")
    @Transactional
    public ResponseEntity<String> toggleLike(@PathVariable Long id, HttpSession session) {
        String userEmail = (String) session.getAttribute("loginEmail");

        if (userEmail == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }

        FreeBoard freeBoardEntity = freeBoardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        Optional<FreeBoardLike> existingLike = freeBoardLikeRepository.findByUserEmailAndFreeBoardId(userEmail, id);

        existingLike.ifPresentOrElse(
                like -> {
                    freeBoardLikeRepository.delete(like);
                    freeBoardEntity.setLikeCount(freeBoardEntity.getLikeCount() - 1);
                },
                () -> {
                    FreeBoardLike newLike = new FreeBoardLike();
                    newLike.setUserEmail(userEmail);
                    newLike.setFreeBoard(freeBoardEntity);
                    freeBoardLikeRepository.save(newLike);
                    freeBoardEntity.setLikeCount(freeBoardEntity.getLikeCount() + 1);
                }
        );

        freeBoardRepository.save(freeBoardEntity);

        return new ResponseEntity<>("좋아요 상태 변경됨", HttpStatus.OK);
    }


}
