package com.glappy.login.service;

import java.util.List;

import com.glappy.login.model.BoardVO;

public interface BBSService {
	public List<BoardVO> selectAll();
	public BoardVO findById(BoardVO boardVO);
	
	public List<BoardVO> findByUserId(String b_userid);
	public BoardVO updateHit(long id, String b_userid);
	
	public int insert(BoardVO boardVO);
	public int update(BoardVO boardVO);
	public int delete(long id);
}