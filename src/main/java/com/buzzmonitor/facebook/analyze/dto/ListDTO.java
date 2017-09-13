package com.buzzmonitor.facebook.analyze.dto;

public class ListDTO<T> {

	private T data;
	private Page paging;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Page getPaging() {
		return paging;
	}

	public void setPaging(Page paging) {
		this.paging = paging;
	}

}
