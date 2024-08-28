package com.api.hateoas.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

  @GetMapping(value = {"","/"})
  public String index(HttpServletRequest request, Model model){
    Object id = request.getSession().getAttribute("id");
    model.addAttribute("id", id != null ? id : "");
    return "index";
  }

  @GetMapping("/view/login")
  public String login(HttpServletRequest request, Model model){
    return "user/login";
  }

  @GetMapping("/view/join")
  public String join(){
    return "user/join";
  }

  @GetMapping("/view/update")
  public String update(HttpServletRequest request, Model model){
    Object id = request.getSession().getAttribute("id");
    model.addAttribute("id", id != null ? id : "");
    return "user/update";
  }

  @GetMapping("/view/quit")
  public String quit(HttpServletRequest request, Model model){
    Object id = request.getSession().getAttribute("id");
    model.addAttribute("id", id != null ? id : "");
    return "user/quit";
  }

}
