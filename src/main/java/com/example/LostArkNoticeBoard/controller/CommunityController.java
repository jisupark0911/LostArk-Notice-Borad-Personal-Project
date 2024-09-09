package com.example.LostArkNoticeBoard.controller;

import com.example.LostArkNoticeBoard.dto.jobBoardForm;
import com.example.LostArkNoticeBoard.entity.FreeBoard;
import com.example.LostArkNoticeBoard.dto.freeBoardForm;
import com.example.LostArkNoticeBoard.entity.JobBoard;
import com.example.LostArkNoticeBoard.repository.FreeBoardRepository;
import com.example.LostArkNoticeBoard.repository.JobBoardRepository;
import com.example.LostArkNoticeBoard.service.FreeBoardCommentService;
import com.example.LostArkNoticeBoard.service.FreeBoardService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import com.example.LostArkNoticeBoard.dto.freeBoardCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

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
    public String freeBoardcreate(freeBoardForm form,Model model,HttpSession session){ //form은 dto를 가르킨다.
        FreeBoard freeBoard = form.freeEntity();//dto를 엔티티로 변환
        log.info(freeBoard.toString());
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        freeBoard.setUsername(userName);
        FreeBoard freeBoardsave = freeBoardRepository.save(freeBoard);
        freeBoardRepository.save(freeBoard);

        return "redirect:/community/freeBoard/" + freeBoardsave.getId();
    }

    @GetMapping("/community/freeBoard/{id}")//해당번호 게시글 조회
    public String freeBoardshow(@PathVariable Long id, Model model){
        log.info("id = " + id);
        FreeBoard freeBoardEntity = freeBoardRepository.findById(id).orElse(null);
        List<freeBoardCommentDto> freeBoardCommentDtos = freeBoardCommentService.freeBoardComments(id);
        model.addAttribute("freeBoard", freeBoardEntity);
        model.addAttribute("freeBoardCommentDtos",freeBoardCommentDtos);

        return "community/freeBoard_show";
    }

    @GetMapping("/community/freeBoard/{id}/edit")
    public String freeBoardedit(@PathVariable Long id, Model model){
        FreeBoard freeBoardEntity = freeBoardRepository.findById(id).orElse(null);
        model.addAttribute("freeBoard",freeBoardEntity);

        return "community/freeBoard_edit";
    }

    @PostMapping("/community/freeBoard/update")
    public String freeBoardupdate(freeBoardForm form){
        log.info(form.toString());
        FreeBoard freeBoardEntity = form.freeEntity();
        FreeBoard freeBoardtarget = freeBoardRepository.findById(freeBoardEntity.getId()).orElse(null);
        if(freeBoardtarget != null){
            freeBoardtarget = freeBoardRepository.save(freeBoardEntity);
        }
        return "redirect:/community/freeBoard/"+freeBoardEntity.getId();
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
