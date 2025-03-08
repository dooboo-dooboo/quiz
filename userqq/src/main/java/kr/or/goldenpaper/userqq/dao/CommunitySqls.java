package kr.or.goldenpaper.userqq.dao;

public class CommunitySqls {
	public static final String SELECT_ALL_COMMUNITY = "SELECT * FROM community";
	public static final String SELECT_LAST = "SELECT * FROM community ORDER BY post_id DESC LIMIT 1";
	public static final String SELECT_BY_KEYWORD = "SELECT * FROM community WHERE content LIKE :content";
	public static final String SELECT_TEN = "SELECT * FROM community ORDER BY post_id DESC LIMIT :start ,10";
	public static final String COUNT_ALL = "SELECT COUNT(*) AS post_id FROM community";
}
