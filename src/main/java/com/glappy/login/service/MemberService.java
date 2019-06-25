package com.glappy.login.service;

import java.util.List;

import javax.validation.Valid;

import com.glappy.login.model.MemberVO;

public interface MemberService {
	public List<MemberVO> selectAll();
	public MemberVO findByUserId(String m_userid);

	public int insert(MemberVO memberVO);
	public int update(MemberVO memberVO);
	public int delete(long id);
	public int save(@Valid MemberVO memberVO);
}