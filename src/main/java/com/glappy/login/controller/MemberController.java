package com.glappy.login.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.glappy.login.model.MemberVO;
import com.glappy.login.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
@SessionAttributes({"memberVO"})
public class MemberController {
	@Autowired
	MemberService mService;
	
	@ModelAttribute("memberVO")
	public MemberVO newMemberVO() {
		MemberVO vo=new MemberVO();
		return vo;
	}
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join(@ModelAttribute("memberVO") MemberVO memberVO,Model model, 
						SessionStatus session) {
		session.setComplete();
		model.addAttribute("BODY","JOIN_FORM");
		return "home";
	}
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(@ModelAttribute("memberVO") @Valid MemberVO memberVO, 
						BindingResult result, Model model,SessionStatus session) {
		if(result.hasErrors()) {
			log.debug("HasError");
			model.addAttribute("BODY","JOIN_FORM");
			return "home";
		}else {
			int ret=mService.save(memberVO);
			session.setComplete();
			log.debug("No Error");
			return "redirect:/member/join";
		}
	}
	@RequestMapping(value="/join1",method=RequestMethod.GET)
	public String join(Model model) {
		MemberVO vo=new MemberVO();
		model.addAttribute("memberVO",vo);
		return "body/join_form";
	}
	@RequestMapping(value="/join2",method=RequestMethod.GET)
	public String join() {
		return "body/join_form";
	}
	@RequestMapping(value="/mypage",method=RequestMethod.GET)
	public String my_page(@ModelAttribute("memberVO") MemberVO memberVO, Model model, 
							HttpSession session) {
		model.addAttribute("memberVO",(MemberVO)session.getAttribute("login_info"));
		model.addAttribute("ACTION","UPDATE");
		model.addAttribute("BODY","JOIN_FORM");
		return "home";
	}
}