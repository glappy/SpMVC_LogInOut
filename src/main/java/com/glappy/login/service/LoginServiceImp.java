package com.glappy.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.glappy.login.mapper.LoginDao;
import com.glappy.login.model.BoardVO;
import com.glappy.login.model.MemberVO;

@Service
public class LoginServiceImp implements LoginService {
	@Autowired
	LoginDao lDao;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public MemberVO getMemberInfo(MemberVO loginVO) {
		String m_userid = loginVO.getM_userid();
		MemberVO vo = lDao.getMemberInfo(m_userid);

		String cryptPass = vo.getM_password();
		String encodedPassword = vo.getM_password();

		String plainPass = loginVO.getM_password();
		String rawPassword = loginVO.getM_password();

		boolean match = encoder.matches(rawPassword, encodedPassword);
		if (match) {
			return vo;
		} else {
			return null;
		}
	}
}