package com.glappy.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.glappy.login.model.MemberVO;
import com.glappy.login.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes({"memberVO"})
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginService lService;
	
	@ModelAttribute("memberVO")
	public MemberVO login_info() {
		log.debug("NEW memberVO");
		return new MemberVO();
	}

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(@RequestParam(required=false) String LOGIN_MSG,
			@ModelAttribute MemberVO memberVO,Model model) {
		
		log.debug("LOGIN:" + memberVO.toString());
		log.debug("LOGIN MSG:" + LOGIN_MSG);
		model.addAttribute("LOGIN_MSG", LOGIN_MSG);
		model.addAttribute("BODY","LOGIN_FORM");
		return "home";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(@ModelAttribute MemberVO memberVO, Model model, HttpSession session) {
		MemberVO vo = lService.getMemberInfo(memberVO);
		if(vo != null) {
			session.setAttribute("login_info", vo);
			log.debug(memberVO.toString());
		} else {
			session.removeAttribute("login_info");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(@ModelAttribute MemberVO memberVO, HttpSession session,
						SessionStatus status) {
		
		log.debug("LOGOUT");
		session.removeAttribute("login_info");
		status.setComplete();
		return "redirect:/";
	}
}