package com.example.LostArkNoticeBoard.controller;

import com.example.LostArkNoticeBoard.Model.Event;
import com.example.LostArkNoticeBoard.Model.Notice;
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

@Slf4j
@Controller
public class MainController {

    @Autowired
    private HttpSession session;

    @Autowired
    private LostArkApiService lostArkApiService;


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
            // 상위 5개만 추출
            Notice[] limitedNotices = Arrays.copyOf(notices, Math.min(notices.length, 6));
            model.addAttribute("notices", limitedNotices);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "main"; // main.mustache를 렌더링
    }

}
