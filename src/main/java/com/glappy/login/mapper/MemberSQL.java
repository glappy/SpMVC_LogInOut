package com.glappy.login.mapper;

import org.apache.ibatis.jdbc.SQL;

public class MemberSQL {
	public String member_insert_sql() {
		SQL sql=new SQL()
				.INSERT_INTO("tbl_member")
				.INTO_COLUMNS("m_userid").INTO_VALUES("#{m_userid}")
				.INTO_COLUMNS("m_password").INTO_COLUMNS("#{m_password}")
				.INTO_COLUMNS("m_name").INTO_VALUES("#{m_name}")
				.INTO_COLUMNS("m_tel").INTO_VALUES("#{m_tel}");
		return sql.toString();
	}
	public String member_update_sql() {
		SQL sql=new SQL()
				.UPDATE("tbl_member")
				.SET("m_userid=#{m_userid}")
				.SET("m_password=#{m_password}")
				.SET("m_name=#{m_name}")
				.SET("m_tel=#{m_tel}")
				.WHERE("m_userid=#{m_userid}");
		return sql.toString();
	}
}
