package com.glappy.login.mapper;

import org.apache.ibatis.annotations.Select;

import com.glappy.login.model.MemberVO;

public interface LoginDao {
	@Select("select * from tbl_member where m_userid=#{m_userid}")
	public MemberVO getMemberInfo(String m_userid);
}
