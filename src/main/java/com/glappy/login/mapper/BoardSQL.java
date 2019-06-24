package com.glappy.login.mapper;

import org.apache.ibatis.jdbc.SQL;

public class BoardSQL {
	public String board_inset_sql() {
		SQL sql=new SQL()
				.INSERT_INTO("tbl_board")
				.INTO_COLUMNS("id").INTO_VALUES("SEQ_BOARD")
				.INTO_COLUMNS("b_userid").INTO_VALUES("#{b_userid}")
				.INTO_COLUMNS("b_date").INTO_VALUES("#{b_date}")
				.INTO_COLUMNS("b_time").INTO_VALUES("#{b_time}")
				.INTO_COLUMNS("b_title").INTO_VALUES("#{b_title}")
				.INTO_COLUMNS("b_content").INTO_VALUES("#{b_content}")
				.INTO_COLUMNS("b_hit").INTO_VALUES("#{b_hit}")
				.INTO_COLUMNS("b_image").INTO_VALUES("#{b_image}");
		return sql.toString();
	}
	public String board_update_sql() {
		SQL sql=new SQL()
				.UPDATE("tbl_board")
				.SET("b_userid=#{b_userid}")
				.SET("b_date=#{b_date}")
				.SET("b_time=#{b_time}")
				.SET("b_title=#{b_title}")
				.SET("b_content=#{b_content}")
				.SET("b_hit=#{b_hit}")
				.SET("b_image=#{b_image}")
				.WHERE("id=#{id}");
		return sql.toString();
	}
}
