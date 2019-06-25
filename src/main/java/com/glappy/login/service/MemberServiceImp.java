package com.glappy.login.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.glappy.login.mapper.MemberDao;
import com.glappy.login.model.MemberVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class MemberServiceImp implements MemberService {

	@Autowired
	MemberDao mDao;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Override
	public List<MemberVO> selectAll() {
		return null;
	}

	@Override
	public MemberVO findByUserId(String m_userid) {
		return null;
	}

	@Override
	public int insert(MemberVO memberVO) {
		String plainPass=memberVO.getM_password();
		String crypPass=encoder.encode(plainPass);
		log.debug("비밀번호 : "+plainPass);
		log.debug("암호 비밀번호 : "+crypPass);
		
		memberVO.setM_password(crypPass);
		int ret=mDao.insert(memberVO);
		return ret;
	}

	@Override
	public int update(MemberVO memberVO) {
		String plainPass=memberVO.getM_password();
		String crypPass=encoder.encode(plainPass);
		memberVO.setM_password(crypPass);
		int ret=mDao.update(memberVO);
		return ret;
	}

	@Override
	public int delete(long id) {
		return 0;
	}

	@Override
	public int save(@Valid MemberVO memberVO) {
		int ret=0;
		MemberVO vo=mDao.findByUserId(memberVO.getM_userid());
		if(vo==null) {
			this.insert(memberVO);
			ret=1;
		}else {
			String rawString=memberVO.getM_password();
			String encodeString=vo.getM_password();
			
			if(encoder.matches(rawString, encodeString)) {
				this.update(memberVO);
				ret=2;
			}else {
				ret=-1;
			}
		}
		return ret;
	}
}