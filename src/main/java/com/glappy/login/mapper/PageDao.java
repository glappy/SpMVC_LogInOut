package com.glappy.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.glappy.login.model.BoardVO;

public interface PageDao {
	@Select("select count(*) from tbl_board")
	public long getCount();

	@Select("select * from (select rownum R, B.* from tbl_board B where b_date>'0000-00-00') M "
			+ "where r between #{start} and #{end}")
	public List<BoardVO> selectPage(@Param("start") long start, @Param("end") long end);

	@Select("select M.* from (select row_number() over (order by b_date desc, b_time desc) R, "
			+ "b_date, b_time, b_title, b_content from tbl_board) M " + "where M.R between #{start} and #{end}")
	public List<BoardVO> selectPage2(@Param("start") long start, @Param("end") long end);
}