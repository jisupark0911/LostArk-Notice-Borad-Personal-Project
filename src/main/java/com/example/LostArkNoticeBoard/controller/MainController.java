package com.example.LostArkNoticeBoard.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private HttpSession session;

    @GetMapping("/main")
    public String mainPage(Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        model.addAttribute("loginEmail", loginEmail);
        return "main"; // main.mustache를 렌더링
    }

}
