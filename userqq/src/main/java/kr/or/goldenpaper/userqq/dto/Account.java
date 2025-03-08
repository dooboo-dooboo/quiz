package kr.or.goldenpaper.userqq.dto;

import org.json.JSONArray;
import org.json.simple.JSONObject;

public class Account {
	private String id;
	private String nickname;
	private String password;
	private int xp;
	private JSONArray cleared_quiz;
	public int getXp() {
		return xp;
	}
	public void setXp(int xp) {
		this.xp = xp;
	}
	public JSONArray getCleared_quiz() {
		return cleared_quiz;
	}
	public void setCleared_quiz(JSONArray cleared_quiz) {
		this.cleared_quiz = cleared_quiz;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", nickname=" + nickname + ", password=" + password + ", xp=" + xp
				+ ", cleared_quiz=" + cleared_quiz + "]";
	}
}
