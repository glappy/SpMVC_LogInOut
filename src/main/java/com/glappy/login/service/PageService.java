package com.glappy.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glappy.login.mapper.PageDao;
import com.glappy.login.model.BoardVO;
import com.glappy.login.model.PageVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PageService {
	@Autowired
	PageDao pDao;

	public long getCount() {
		return pDao.getCount();
	}

	public List<BoardVO> pageList(PageVO pageVO) {
		long c = pDao.getCount();
		long sRow = (pageVO.getCurrentPageNo() - 1) * pageVO.getListPerPage();
		long eRow = sRow + pageVO.getListPerPage();

		log.debug("COUNT " + c);
		log.debug("sRow " + sRow);
		log.debug("eRow " + eRow);

		List<BoardVO> bbsList = pDao.selectPage2(sRow, eRow);
		return bbsList;
	}

	public PageVO page_select(long page_no) {
		long totalCount = pDao.getCount();
		if (totalCount == 0)
			return null;
		if (page_no == 0)
			page_no = 1;

		long listPerPage = 10;
		long totalPage = (long) (totalCount / listPerPage);
		long currPage = page_no;

		log.debug("totalPage : " + totalPage);

		PageVO pageVO = new PageVO();
		pageVO.setTotalCount(totalCount);
		pageVO.setListPerPage(listPerPage);
		pageVO.setCurrentPageNo(currPage);
		pageVO.setEndPageNo(totalPage);

		long finalPage = (totalCount + (listPerPage - 1)) / listPerPage;
		log.debug("finalPage " + finalPage);
		log.debug("currPage " + currPage);

		boolean isNowFirst = currPage == 1 ? true : false;
		boolean isNowFinal = currPage == finalPage ? true : false;
		long startPage = ((currPage - 1) / listPerPage) + listPerPage + 1;
		long endPage = startPage + listPerPage - 1;

		if (endPage > finalPage)
			endPage = finalPage;

		pageVO.setFirstPageNo(1);
		if (isNowFirst)
			pageVO.setPrePageNo(1);
		else
			pageVO.setPrePageNo((currPage - 1) < 1 ? 1 : currPage - 1);
		
		pageVO.setStartUpPageNo(startPage);
		pageVO.setEndPageNo(endPage);
		if (isNowFinal)
			pageVO.setNextPageNo(finalPage);
		else
			pageVO.setNextPageNo(((currPage + 1) > finalPage ? finalPage : (currPage + 1)));
		pageVO.setFinalPageNo(finalPage);
		
		log.debug("start " + pageVO.getStartUpPageNo());
		log.debug("end " + pageVO.getEndPageNo());
		log.debug("next " + pageVO.getNextPageNo());
		
		return pageVO;
	}

}