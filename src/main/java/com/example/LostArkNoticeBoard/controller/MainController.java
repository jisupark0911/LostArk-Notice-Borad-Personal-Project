package com.example.LostArkNoticeBoard.controller;

import com.example.LostArkNoticeBoard.Model.Abyss;
import com.example.LostArkNoticeBoard.Model.Event;
import com.example.LostArkNoticeBoard.Model.Guardian;
import com.example.LostArkNoticeBoard.Model.Notice;
import com.example.LostArkNoticeBoard.entity.FreeBoard;
import com.example.LostArkNoticeBoard.service.FreeBoardService;
import com.example.LostArkNoticeBoard.service.LostArkApiService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private HttpSession session;

    @Autowired
    private LostArkApiService lostArkApiService;

    @Autowired
    private FreeBoardService freeBoardService;


    @ModelAttribute
    public void loginNickName(Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        try {
            Event[] events = lostArkApiService.getEvents();
            model.addAttribute("events", events);
            Notice[] notices = lostArkApiService.getNotices();
            // 상위 6개만 추출
            Notice[] limitedNotices = Arrays.copyOf(notices, Math.min(notices.length, 6));
            model.addAttribute("notices", limitedNotices);

            Abyss[] abyss = lostArkApiService.getAbyss();
            Guardian guardians = lostArkApiService.getGuardian();

            if (abyss == null || guardians == null) {
                model.addAttribute("challengeError", "API가 삭제 되었습니다.");
            } else {
                model.addAttribute("abyss", abyss);
                model.addAttribute("guardians", guardians);
            }
            List<FreeBoard> freeBoardList = freeBoardService.getFreeBoardList();
            // 자유게시판 상위 6개만 추출
            List<FreeBoard> limitFreeBoards = freeBoardList.stream()
                    .limit(6)
                    .toList();
            model.addAttribute("limitFreeBoards", limitFreeBoards);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("challengeError", "API가 삭제 되었습니다.");
        }
        return "main"; // main.mustache를 렌더링
    }

}
