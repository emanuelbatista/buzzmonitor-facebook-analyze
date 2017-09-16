package com.buzzmonitor.facebook.analyze.dto;

import java.util.List;

public class ListDTO<T> {

	private List<T> data;
	private Page paging;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Page getPaging() {
		return paging;
	}

	public void setPaging(Page paging) {
		this.paging = paging;
	}

}
