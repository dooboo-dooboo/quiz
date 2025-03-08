package kr.or.goldenpaper.userqq.dao;

public class QuizSqls {
	public static final String SELECT_ALL_QUIZ = "SELECT * FROM quiz";
	public static final String SELECT_QUIZ_BY_ID = "SELECT * FROM quiz WHERE quiz_id=:quizId";
	public static final String SELECT_TEN_QUIZ = "SELECT * FROM quiz ORDER BY quiz_id DESC LIMIT :start ,10";
	public static final String COUNT_ALL_QUIZ = "SELECT COUNT(*) AS quiz_id FROM quiz";
	public static final String UPDATE_SOLVED_COUNT = "UPDATE quiz SET solved_count=:solvedCount WHERE quiz_id=:quizId";
	public static final String SELECT_LAST_QUIZ = "SELECT * FROM quiz ORDER BY quiz_id DESC LIMIT 1";
}
