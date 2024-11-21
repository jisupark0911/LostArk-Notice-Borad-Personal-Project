package com.example.LostArkNoticeBoard.controller;

import com.example.LostArkNoticeBoard.dto.jobBoardForm;
import com.example.LostArkNoticeBoard.entity.FreeBoard;
import com.example.LostArkNoticeBoard.dto.freeBoardForm;
import com.example.LostArkNoticeBoard.entity.FreeBoardLike;
import com.example.LostArkNoticeBoard.entity.JobBoard;
import com.example.LostArkNoticeBoard.repository.FreeBoardLikeRepository;
import com.example.LostArkNoticeBoard.repository.FreeBoardRepository;
import com.example.LostArkNoticeBoard.repository.JobBoardRepository;
import com.example.LostArkNoticeBoard.service.FreeBoardCommentService;
import com.example.LostArkNoticeBoard.service.FreeBoardService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import com.example.LostArkNoticeBoard.dto.freeBoardCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j // 로깅사용가능
@Controller


public class CommunityController {
    @Autowired
    private FreeBoardRepository freeBoardRepository;
    @Autowired
    private JobBoardRepository jobBoardRepository;
    @Autowired
    private HttpSession session;
    @Autowired
    private FreeBoardCommentService freeBoardCommentService;
    @Autowired
    private FreeBoardService freeBoardService;
    @Autowired
    private FreeBoardLikeRepository freeBoardLikeRepository;



    @ModelAttribute
    public void loginNickName(Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
    }
    @ModelAttribute
    public void loginUserEmail(Model model) {
        String userEmail = (String) session.getAttribute("userEmail");
        model.addAttribute("userEmail", userEmail);
    }

    @GetMapping("/community/freeBoard")
    public String freeBoardIndex(Model model, HttpSession session) {

        List<FreeBoard> freeBoardList = freeBoardRepository.findAll();
        model.addAttribute("freeBoardList", freeBoardList);

        return "community/freeBoard";
    }

    @GetMapping("/community/freeBoard/new")
    public String freeBoardnew(){

        return "community/freeBoard_new";
    }

    @PostMapping("/community/freeBoard/create")
    public String freeBoardcreate(freeBoardForm form, Model model, HttpSession session) {
        FreeBoard freeBoard = form.freeEntity(); // DTO를 엔티티로 변환
        log.info(freeBoard.toString());

        String userEmail = (String) session.getAttribute("loginEmail");
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);

        freeBoard.setUsername(userName);
        freeBoard.setUserEmail(userEmail);

        FreeBoard freeBoardsave = freeBoardRepository.save(freeBoard);
        freeBoardRepository.save(freeBoard);

        return "redirect:/community/freeBoard/" + freeBoardsave.getId();
    }


    @GetMapping("/community/freeBoard/{id}")
    public String freeBoardshow(@PathVariable Long id, Model model, HttpSession session) {
        log.info("id = " + id);
        FreeBoard freeBoardEntity = freeBoardRepository.findById(id).orElse(null);

        if (freeBoardEntity != null) {
            freeBoardEntity.setViewCount(freeBoardEntity.getViewCount() + 1);
            freeBoardRepository.save(freeBoardEntity);
        }

        String userEmail = (String) session.getAttribute("loginEmail");
        boolean isAuthor = false;
        if (userEmail != null && userEmail.equals(freeBoardEntity.getUserEmail())) {
            isAuthor = true;
        }

        boolean isLiked = false;
        if (userEmail != null) {
            Optional<FreeBoardLike> existingLike = freeBoardLikeRepository.findByUserEmailAndFreeBoardId(userEmail, id);
            isLiked = existingLike.isPresent();
        }

        List<freeBoardCommentDto> freeBoardCommentDtos = freeBoardCommentService.freeBoardComments(id);

        model.addAttribute("freeBoard", freeBoardEntity);
        model.addAttribute("freeBoardCommentDtos", freeBoardCommentDtos);
        model.addAttribute("isAuthor", isAuthor);
        model.addAttribute("isLiked", isLiked);

        return "community/freeBoard_show";
    }




    @GetMapping("/community/freeBoard/{id}/edit")
    public String freeBoardedit(@PathVariable Long id, Model model){
        FreeBoard freeBoardEntity = freeBoardRepository.findById(id).orElse(null);
        model.addAttribute("freeBoard",freeBoardEntity);

        return "community/freeBoard_edit";
    }

    @PostMapping("/community/freeBoard/update")
    public String freeBoardupdate(freeBoardForm form, Model model, HttpSession session){
        log.info(form.toString());

        // form에서 엔티티로 변환
        FreeBoard freeBoardEntity = form.freeEntity();
        String userName = (String) session.getAttribute("userName");
        String userEmail = (String) session.getAttribute("loginEmail");
        freeBoardEntity.setUserEmail(userEmail);
        freeBoardEntity.setUsername(userName);
        model.addAttribute("userName", userName);

        FreeBoard freeBoardtarget = freeBoardRepository.findById(freeBoardEntity.getId()).orElse(null);

        if(freeBoardtarget != null){
            freeBoardtarget.setTitle(freeBoardEntity.getTitle());
            freeBoardtarget.setContent(freeBoardEntity.getContent());
            freeBoardtarget.setUserEmail(freeBoardEntity.getUserEmail());
            freeBoardtarget.setUsername(freeBoardEntity.getUsername());
            freeBoardRepository.save(freeBoardtarget);
        }

        return "redirect:/community/freeBoard/" + freeBoardEntity.getId();
    }


    @GetMapping("/community/freeBoard/{id}/delete")
    public String freeBoarddelete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");
        FreeBoard freeBoardtarget = freeBoardRepository.findById(id).orElse(null);
        log.info(freeBoardtarget.toString());
        if(freeBoardtarget != null) {
            freeBoardRepository.delete(freeBoardtarget);
            rttr.addFlashAttribute("msg","삭제됐습니다.");
        }
        return "redirect:/community/freeBoard";
    }


    @PostMapping("/community/freeBoard/{id}/toggleLike")
    @Transactional
    public String toggleLike(@PathVariable Long id, HttpSession session) {
        String userEmail = (String) session.getAttribute("loginEmail");

        if (userEmail == null) {
            return "redirect:/logins/login";
        }

        FreeBoard freeBoardEntity = freeBoardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

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

        return "redirect:/community/freeBoard/" + id;
    }







    @GetMapping("/community/jobBoard")
    public String jobBoardindex(Model model){
        ArrayList<JobBoard> jobBoardEntityList = jobBoardRepository.findAll();
        model.addAttribute("jobBoardList", jobBoardEntityList);

        return "community/jobBoard";
    }

    @GetMapping("/community/jobBoard/new")
    public String jobBoardnew(){

        return "community/jobBoard_new";
        }

    @PostMapping("/community/jobBoard/create")
    public String jobBoardcreate(jobBoardForm form){
        JobBoard jobBoard = form.jobEntity();
        log.info(jobBoard.toString());
        JobBoard jobBoardsave = jobBoardRepository.save(jobBoard);
        log.info(jobBoardsave.toString());

        return "redirect:/community/jobBoard/"+jobBoardsave.getId();


    }

    @GetMapping("/community/jobBoard/{id}")
    public String jobBoardshow(@PathVariable Long id, Model model){
        log.info("id = " + id);
        JobBoard jobBoardEntity = jobBoardRepository.findById(id).orElse(null);
        model.addAttribute("jobBoard", jobBoardEntity);

        return "community/jobBoard_show";
    }

    @GetMapping("/community/jobBoard/{id}/edit")
    public String jobBoardedit(@PathVariable Long id, Model model) {
        JobBoard jobBoardEntity = jobBoardRepository.findById(id).orElse(null);
        model.addAttribute("jobBoard",jobBoardEntity);

        return "community/jobBoard_edit";
    }

    @PostMapping("/community/jobBoard/update")
    public String jobBoardupdate(jobBoardForm form){
        log.info(form.toString());
        JobBoard jobBoardEntity = form.jobEntity();
        JobBoard jobBoardtarget = jobBoardRepository.findById(jobBoardEntity.getId()).orElse(null);
        if(jobBoardtarget != null){
            jobBoardtarget = jobBoardRepository.save(jobBoardEntity);
        }
        return "redirect:/community/jobBoard/"+jobBoardEntity.getId();
    }


    @GetMapping("/community/jobBoard/{id}/delete")
    public String jobBoarddelete(@PathVariable Long id, RedirectAttributes rttr) {
        JobBoard jobBoardtarget = jobBoardRepository.findById(id).orElse(null);
        if(jobBoardtarget != null){
            jobBoardRepository.delete(jobBoardtarget);
            rttr.addFlashAttribute("msg","삭제됐습니다.");
        }
        return "redirect:/community/jobBoard";
    }

    @GetMapping("/community/freeBoard/search")
    public String freeBoardSearch(@RequestParam("keyword") String keyword, Model model) {
        List<FreeBoard> results = freeBoardService.searchByTitleOrContent(keyword);
        model.addAttribute("freeBoardResults", results);
        model.addAttribute("freeBoardKeyword", keyword);

        return "community/freeBoard";
    }


}
