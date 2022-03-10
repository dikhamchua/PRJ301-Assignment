package com.se1610.model;

public class Comment {
	private String content;
	private Long userId;
	private Long newsId;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newId) {
		this.newsId = newId;
	}
}
