package com.example.thuctap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/login/form")
    public String loginFrom(Model model) {
        model.addAttribute("message", "Vui Lòng Đăng Nhập");
        return "security/login";
    }

    @RequestMapping("login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Sai Thông Tin Đăng Nhập");
        return "security/login";
    }

    @RequestMapping("/login/success")
    public String loginSuccess(Model model){
        model.addAttribute("message", "Đăng Nhập Thành Công");
        return "security/login";
    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("message", "Đăng Xuất Thành Công");
        return "redirect:/product";
    }
}
