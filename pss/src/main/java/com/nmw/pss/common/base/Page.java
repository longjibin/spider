package com.nmw.pss.common.base;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <p>Description:分页对象<p>
 * @author longjibin
 * @version 2017-1-5
 * <p>Copyright:Copyright (c) 2017-1-5<p>
 */
public class Page<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//总记录数
	private Integer recordCount;
	
	//每页的数据条数
	private Integer pageSize=30;
	
	//总页数
	private Integer pageCount;
	
	//当前页号
	private Integer pageNow=1;
	
	//上一页页号
	private Integer preNum;
	
	//下一页页号
	private Integer nextNum;
	
	//显示的页号数量
	private Integer showNum=9;
	
	//开始页号
	private Integer startNum;
	
	//结束页号
	private Integer endNum;
	
	//记录集合
	private List<T> records;
	
	//封装的查询对象
	private T queryObj;
	
	//排序条件
	private String orderBy;
	
	
	public Page() {
		super();
	}

	public Page(Integer pageSize, Integer showNum) {
		super();
		this.pageSize = pageSize;
		this.showNum = showNum;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getPreNum() {
		return preNum;
	}

	public void setPreNum(Integer preNum) {
		this.preNum = preNum;
	}

	public Integer getNextNum() {
		return nextNum;
	}

	public void setNextNum(Integer nextNum) {
		this.nextNum = nextNum;
	}

	public Integer getShowNum() {
		return showNum;
	}

	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}

	public Integer getStartNum() {
		return startNum;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}

	public Integer getEndNum() {
		return endNum;
	}

	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public T getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(T queryObj) {
		this.queryObj = queryObj;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 
	 * <p>Description:计算分页总数<p>
	 * @author longjibin
	 * @version 下午10:07:18
	 * @param recordCount
	 * @modifidate 下午10:07:18
	 * <p>modifiContent:<p>
	 * @return
	 */
	public void calculatePageCount(){
		//计算总页数
		if(recordCount%pageSize==0){
			pageCount=recordCount/pageSize;
		}else{
			pageCount=recordCount/pageSize+1;
		}
		pageNow=pageNow>pageCount?1:pageNow;
		preNum=pageNow-1;
		nextNum=pageNow+1;
		//计算开始页号和结束页号
		if(pageCount<showNum){
			startNum=1;
			endNum=pageCount;
		}else{
			//当showNum为奇数时计算偏移量
			if(showNum%2!=0){
				Integer offset=(showNum-1)/2;
				if(pageNow-offset>=1&&pageNow+offset<=pageCount){
					startNum=pageNow-offset;
					endNum=pageNow+offset;
				}else if(pageNow-offset<1){
					startNum=1;
					endNum=showNum;
				}else if(pageNow+offset>pageCount){
					startNum=pageCount-showNum+1;
					endNum=pageCount;
				}
			}else{
				System.out.println(showNum+"为偶数");
			}
		}
	}
}
