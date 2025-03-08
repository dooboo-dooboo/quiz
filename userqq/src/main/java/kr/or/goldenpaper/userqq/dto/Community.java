package kr.or.goldenpaper.userqq.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Community {
	private int post_id;
	private String user_id;
	private String title;
	private String content;
	private String created_time;
	private int like_count;
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like) {
		this.like_count = like;
	}
	@Override
	public String toString() {
		return "Community [post_id=" + post_id + ", user_id=" + user_id + ", title=" + title + ", content=" + content
				+ ", created_time=" + created_time + ", like_count=" + like_count + "]";
	}
}
