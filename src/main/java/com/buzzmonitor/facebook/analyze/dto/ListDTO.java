package com.buzzmonitor.facebook.analyze.dto;

public class ListDTO<T> {

	private T data;
	private Page paging;
	private String next;
	private String previous;

	public static class Page {

		private Cursors cursors;

		public static class Cursors {

			private String before;
			private String after;

			public String getBefore() {
				return before;
			}

			public void setBefore(String before) {
				this.before = before;
			}

			public String getAfter() {
				return after;
			}

			public void setAfter(String after) {
				this.after = after;
			}

		}

		public Cursors getCursors() {
			return cursors;
		}

		public void setCursors(Cursors cursors) {
			this.cursors = cursors;
		}

	}

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

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

}
