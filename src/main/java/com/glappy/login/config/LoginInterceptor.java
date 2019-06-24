package com.glappy.login.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.glappy.login.model.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("login_info");
		if (memberVO == null) {
			session.removeAttribute("login_info");
			response.sendRedirect("/file/login/login?LOGIN_MSG=LOGIN_REQ");
			return false;
		}
		return true;
	}
}