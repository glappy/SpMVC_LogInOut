package com.glappy.login.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.glappy.login.model.BoardVO;
import com.glappy.login.model.MemberVO;
import com.glappy.login.service.BBSService;
import com.glappy.login.service.FileUpService;
import com.glappy.login.service.PageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SessionAttributes({"bbsVO"})
@Controller
@RequestMapping("/bbs")
public class BBSController {

	@Autowired
	BBSService bService;
	
	@Autowired
	PageService pService;
	
	@Autowired
	FileUpService fService;

	@ModelAttribute("bbsVO")
	public BoardVO newMember() {
		return new BoardVO();
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String bbs_list(Model model) {
		
		List<BoardVO> bbsList = bService.selectAll();
		
		model.addAttribute("BBS_LIST",bbsList);
		model.addAttribute("BODY", "BBS_LIST");
		return "home";
	}
	
	@RequestMapping(value="tag",method=RequestMethod.GET)
	public String bbs_tag(@ModelAttribute("bbsVO") BoardVO boardVO,
				HttpSession session, Model model) {
		
		MemberVO memberVO = (MemberVO)session.getAttribute("login_info");
		
		if(memberVO != null) {
			boardVO.setB_userid(memberVO.getM_userid());	
		} else {
			boardVO.setB_userid("glappy@naver.com");
		}
		
		LocalDateTime ld = LocalDateTime.now();
		DateTimeFormatter fd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter ft = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		String today = ld.format(fd);
		String nt = ld.format(ft);
		
		boardVO.setB_date(today);
		boardVO.setB_time(nt);
		
		model.addAttribute("BODY", "BBS_TAG_WRITE");
		
		return "home";
	}
	
	@RequestMapping(value="/drag",method=RequestMethod.GET)
	public String bbs_write(@ModelAttribute("bbsVO") BoardVO boardVO,
				HttpSession session, Model model) {
		
		MemberVO memberVO = (MemberVO)session.getAttribute("login_info");
		
		if(memberVO == null) {
			model.addAttribute("LOGIN_MSG","LOGIN_REQ");
			return "redirect:/login/login";
		}
		
		LocalDateTime ld = LocalDateTime.now();
		DateTimeFormatter fd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter ft = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		String today1 = ld.format(fd);
		String nt1 = ld.format(ft);
		
		boardVO.setB_date(today1);
		boardVO.setB_time(nt1);
		
		boardVO.setB_userid(memberVO.getM_userid());
		
		model.addAttribute("BODY","BBS_WRITE");
		return "home";
		
	}
	
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String bbs_write(@ModelAttribute("bbsVO") @Valid	BoardVO boardVO,
						BindingResult result, Model model, SessionStatus sStatus) {
		
		if(result.hasErrors()) {
			model.addAttribute("BODY","BBS_WRITE");
			return "home";
		} else {
			
			log.debug(boardVO.toString());
			int ret = 0 ;
			if(boardVO.getId() > 0) {
				ret = bService.update(boardVO);
			} else {
				ret = bService.insert(boardVO);	
			}
			
			sStatus.setComplete();
			model.addAttribute("BODY", "BBS_LIST");
			return "redirect:/bbs/";
		}
	}
	
	@RequestMapping(value="write_tag",method=RequestMethod.POST)
	public String write_tag(@ModelAttribute("bbsVO") BoardVO boardVO,
							BindingResult result, @RequestParam("b_file") 
							MultipartFile b_file) {
		
		log.debug(boardVO.toString());

		List<FieldError> fe = result.getFieldErrors();
		for(FieldError f : fe) {
			log.debug("ERROR : " + f.toString());
		}
		
		if(!b_file.isEmpty()) {
			String saveFile = fService.upload(b_file);
			boardVO.setB_image(saveFile);
		}
		
		bService.insert(boardVO);
		return "redirect:/bbs/";
		
	}
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String bbs_view(
			@ModelAttribute("bbsVO") BoardVO boardVO, 
			Model model,
			HttpSession session) {
		
		MemberVO memberVO 
		= (MemberVO)session.getAttribute("login_info");
		
		if(memberVO == null) {
			model.addAttribute("LOGIN_MSG","LOGIN_REQ");
			return "redirect:/login/login";
		} else {
			
			long id = boardVO.getId();
			log.debug("BOARD ID : " + id);
			
			boardVO 
			= bService.updateHit(id,memberVO.getM_userid());
			
			log.debug(boardVO.toString());
			
			model.addAttribute("bbsVO",boardVO);
			model.addAttribute("BODY","BBS_VIEW");
			return "home";
		}
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String bbs_delete(@RequestParam long id) {
		bService.delete(id);
		return "redirect:/bbs/" ;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String bbs_update(@ModelAttribute("bbsVO") BoardVO boardVO, Model model) {
		
		long id = boardVO.getId();
		log.debug("BOARD ID: " + id);
		boardVO = bService.findById(id);
		
		model.addAttribute("bbsVO",boardVO);
		model.addAttribute("BODY","BBS_WRITE");
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value="/file",method=RequestMethod.POST)
	public String file(@RequestParam MultipartFile file) {
		
		String fileName = fService.upload(file);
		return fileName;
	}
}