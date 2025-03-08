package kr.or.goldenpaper.userqq.dao;

import static kr.or.goldenpaper.userqq.dao.CommunitySqls.SELECT_LAST;
import static kr.or.goldenpaper.userqq.dao.CommunitySqls.SELECT_TEN;
import static kr.or.goldenpaper.userqq.dao.QuizSqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.goldenpaper.userqq.dto.Community;
import kr.or.goldenpaper.userqq.dto.Quiz;

@Repository
public class QuizDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private static RowMapper<Quiz> rowMapper = BeanPropertyRowMapper.newInstance(Quiz.class);
	
	public QuizDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("quiz");
	}
	
	public List<Quiz> selectAllQuiz() {
		return jdbc.query(SELECT_ALL_QUIZ, Collections.emptyMap(), rowMapper);
	}
	
	public List<Quiz> selectQuizById(String string) {
		Map<String, ?> params = Collections.singletonMap("quizId", string);
		return jdbc.query(SELECT_QUIZ_BY_ID, params, rowMapper);
	}
	
	public int insert(Quiz quiz) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(quiz);
		return insertAction.execute(params);
	}
	
	public int getQuizCount() {
		return (int)jdbc.queryForObject(COUNT_ALL_QUIZ, Collections.emptyMap(), Integer.class);
	}
	
	public int updateSolvedCount(int totalCount, int quizId) {
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("solvedCount", totalCount).addValue("quizId", quizId);
		return jdbc.update(UPDATE_SOLVED_COUNT, params);
	}
	
	public List<Quiz> selectLast() {
		return jdbc.query(SELECT_LAST_QUIZ, Collections.emptyMap(), rowMapper);
	}
	
	public List<Quiz> getTen(int start) {
		Map<String, ?> params = Collections.singletonMap("start", start);
		return jdbc.query(SELECT_TEN_QUIZ, params, rowMapper);
	}
}
