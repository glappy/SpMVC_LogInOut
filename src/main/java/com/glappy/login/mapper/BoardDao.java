package com.glappy.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.glappy.login.model.BoardVO;

public interface BoardDao {
	@Select("select * from tbl_board order by b_date desc, b_time desc")
	public List<BoardVO> selectAll();

	@Select("select * from tbl_board where id=#{id}")
	public BoardVO findById(long id);

	@Update("update tbl_board set b_hit=b_hit+1 where id=#{id}")
	public int boardHIt(long id);

	@Select("select * tbl_board b_userid=#{b_userid} order by b_date desc, b_time desc")
	public List<BoardVO> findByUserId(long b_userid);

	@SelectKey(before = true, keyProperty = "id", resultType = Long.class, 
			statement = "select round(dbms_random.value(0,999999),0)from dual")
	@InsertProvider(type = BoardSQL.class, method = "board_insert_sql")
	public int insert(BoardVO boardVO);

	@UpdateProvider(type = BoardSQL.class, method = "board_update_sql")
	public int update(BoardVO boardVO);

	@Delete("delete from tbl_board where id=#{id}")
	public int delete(long id);
}