package com.glappy.login.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageVO {
	long listPerPage;
	long firstPageNo;
	long prePageNo;
	long startUpPageNo;
	long currentPageNo;
	long endPageNo;
	long nextPageNo;
	long finalPageNo;
	long totalCount;
	long pageCount;
	
	public PageVO(long currentPage, long listPerPage) {
		this.currentPageNo=currentPage;
		this.pageCount=10;
		this.listPerPage=(listPerPage!=0)?listPerPage:this.pageCount;
	}
}