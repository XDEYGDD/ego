package cn.gzsxt.common.pojo;

import java.util.List;

public class SerachResult {
	private Long recordCount;
	private List<SearachItem> itemList;
	private Integer pageCount;
	private Integer curPage;
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public List<SearachItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearachItem> itemList) {
		this.itemList = itemList;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public SerachResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}	
