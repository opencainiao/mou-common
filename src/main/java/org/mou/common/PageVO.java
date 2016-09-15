package org.mou.common;

import java.util.List;

import org.mou.common.JsonUtil;

@SuppressWarnings("rawtypes")
public class PageVO {

	private int page; // 当前页
	private int pageCount; // 每页数据条数

	private int startOffset; // 开始条数
	private long total; // 所有数据条数
	private int totalPage; // 所有页数

	private List rows; // 所有查询结果
	private boolean needCalTotal; // 是否需要计算总数据

	public PageVO() {
		page = 1;
		pageCount = 10;
		needCalTotal = false;
		pageCount = Integer.MAX_VALUE;
	}

	/****
	 * 根据总数，计算共有多少页
	 */
	public void calMaxPage() {
		totalPage = (int)(total / pageCount);

		if (total % pageCount != 0) {
			totalPage += 1;
		}
	}


	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStartOffset() {

		if (page == 0) {
			page = 1;
		}
		startOffset = (int)((page - 1) * pageCount);

		return (int)startOffset;
	}

	public void setStartOffset(int startOffset) {
		this.startOffset = startOffset;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public boolean isNeedCalTotal() {
		return needCalTotal;
	}

	public void setNeedCalTotal(boolean needCalTotal) {
		this.needCalTotal = needCalTotal;
	}

	public String toString() {
		return JsonUtil.toJsonStr(this);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	
}
