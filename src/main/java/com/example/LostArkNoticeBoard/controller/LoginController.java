package com.example.LostArkNoticeBoard.controller;

import com.example.LostArkNoticeBoard.dto.loginForm;
import com.example.LostArkNoticeBoard.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private HttpSession session;

    @ModelAttribute
    public void loginNickName(Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
    }


    @GetMapping("/main/login")
    public String userLogin() {
        return "logins/login";

    }

    @PostMapping("/main/login")
    public String userSignIn(@ModelAttribute loginForm form, HttpSession session) {
        loginForm loginresult = loginService.login(form);
        if (loginresult != null) {
            //login 성공
            session.setAttribute("loginEmail", loginresult.getUserEmail());
            session.setAttribute("userName", loginresult.getUserName());
            log.info("User email: " + loginresult.getUserEmail());
            log.info("Session attribute loginEmail: " + session.getAttribute("loginEmail"));

            return "redirect:/main";
        } else {
            return "logins/login";
        }
    }

    @GetMapping("/main/signup")
    public String userSignup() {

        return "logins/signup";
    }


    @PostMapping("/main/signup/create")
    public String userSignupCreate(@ModelAttribute loginForm form) {
        log.info("loginForm = " + form);
        loginService.save(form);
        return "redirect:/main/login";
    }

    @GetMapping("/memberList")
    public String memberFindAll(Model model) {
        List<loginForm> loginFormlist = loginService.findAllLogin();
        model.addAttribute("memberList", loginFormlist);


        return "logins/memberList";
    }

    @GetMapping("/profile/{id}")
    public String profileId(@PathVariable Long id, Model model) {
        loginForm form = loginService.findById(id);
        model.addAttribute("member", form);
        return "logins/memberDetail";
    }

    @GetMapping("/profile/update")
    public String profileUpdate(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        model.addAttribute("loginEmail", loginEmail);
        String myEmail = (String) session.getAttribute("loginEmail");
        loginForm form = loginService.updateForm(myEmail);
        model.addAttribute("updateMember", form);
        return "logins/profileUpdate";
    }

    @PostMapping("/profile/update")
    public String profileupdate(@ModelAttribute loginForm form) {
        loginService.update(form);
        return "redirect:/profile/" + form.getId();
    }

    @GetMapping("/profile/delete/{id}")
    public String deleteProfile(@PathVariable Long id) {
        loginService.deleteById(id);
        return "redirect:/main";
    }

    @GetMapping("/profile/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/main";
    }

    @PostMapping("/main/signup/email-check")
    public @ResponseBody String emailCheck(@RequestParam("userEmail") String userEmail) {
        System.out.println("userEmail = " + userEmail);
        String checkResult = loginService.emailCheck(userEmail);
        return checkResult;
     //  if(checkResult != null){
     //       return"ok";
     //   }else{
     //       return"no";
     //   }

    }

    @PostMapping("/main/signup/name-check")
    public @ResponseBody String nameCheck(@RequestParam("userName") String userName) {
        System.out.println("userName = " + userName);
        return "체크완료";
    }
}
