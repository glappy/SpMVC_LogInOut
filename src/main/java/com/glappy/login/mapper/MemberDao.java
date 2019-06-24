package com.glappy.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.glappy.login.model.MemberVO;

public interface MemberDao {
	@Select("select * from tbl_member")
	public List<MemberVO> selectAll();

	@Select("select * from tbl_member where m_userid=#{m_userid}")
	public MemberVO findByUserId(String m_userid);

	@InsertProvider(type = MemberSQL.class, method = "member_insert_sql")
	public int insert(MemberVO memberVO);

	@UpdateProvider(type = MemberSQL.class, method = "member_update_sql")
	public int update(MemberVO memberVO);

	@Delete("delete from tbl_member where m_userid=#{m_userid}")
	public int delete(String m_userid);
}
