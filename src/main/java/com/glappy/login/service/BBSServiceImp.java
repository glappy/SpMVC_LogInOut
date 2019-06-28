package com.glappy.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glappy.login.mapper.BoardDao;
import com.glappy.login.model.BoardVO;

@Service
public class BBSServiceImp implements BBSService {
	@Autowired
	BoardDao bDao;
	
	@Override
	public List<BoardVO> selectAll() {
		List<BoardVO> bbsList=bDao.selectAll();
		return bbsList;
	}

	@Override
	public BoardVO findById(long id) {
		BoardVO vo=bDao.findById(id);
		return vo;
	}

	@Override
	public List<BoardVO> findByUserId(String b_userid) {
		return null;
	}

	@Override
	@Transactional
	public BoardVO updateHit(long id, String b_userid) {
		BoardVO vo=bDao.findById(id);
		if(!vo.getB_userid().equalsIgnoreCase(b_userid)) {
			bDao.boardHIt(id);
		}
		return vo;
	}
	@Override
	public int insert(BoardVO boardVO) {
		int ret=bDao.insert(boardVO);
		return ret;
	}

	@Override
	public int update(BoardVO boardVO) {
		int ret=bDao.update(boardVO);
		return ret;
	}

	@Override
	public int delete(long id) {
		int ret=bDao.delete(id);
		return ret;
	}
}