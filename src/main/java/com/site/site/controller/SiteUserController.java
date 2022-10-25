package com.site.site.controller;

import com.site.site.form.SiteUserForm;
import com.site.site.service.SiteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class SiteUserController {

    private final SiteUserService siteUserService;

    @GetMapping("/signup")
    public String signup(SiteUserForm siteUserForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid SiteUserForm siteUserForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!siteUserForm.getPassword1().equals(siteUserForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "비밀번호 2개가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
        siteUserService.create(siteUserForm.getUsername(),
                siteUserForm.getPassword1(), siteUserForm.getEmail(), siteUserForm.getNickname());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}
