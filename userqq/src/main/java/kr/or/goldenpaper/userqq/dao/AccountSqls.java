package kr.or.goldenpaper.userqq.dao;

public class AccountSqls {
	public static final String SELECT_ACCOUNT= "SELECT * FROM account";
	public static final String SELECT_NICKNAME = "SELECT nickname FROM account";
	public static final String SELECT_BY_ID = "SELECT * FROM account WHERE id=:userId";
	public static final String DELETE_BY_ID = "DELETE FROM account WHERE id=:userId";
	public static final String UPDATE_XP = "UPDATE account SET xp=:xp WHERE id=:userId";
	public static final String UPDATE_CLEARED_QUIZ = "UPDATE account SET cleared_quiz=:clearedQuiz WHERE id=:userId";
}
