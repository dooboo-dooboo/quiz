package kr.or.goldenpaper.userqq.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.goldenpaper.userqq.dto.Account;

import static kr.or.goldenpaper.userqq.dao.AccountSqls.*;

@Repository
public class AccountDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private static RowMapper<Account> rowMapper = BeanPropertyRowMapper.newInstance(Account.class);

	public AccountDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("account");
	}
	
	public List<Account> selectAllAccount() {
		return jdbc.query(SELECT_ACCOUNT, Collections.emptyMap(), rowMapper);
	}
	
	public int insert(Account account) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(account);
		return insertAction.execute(params);
	}
	
	public int deleteById(String string) {
		Map<String, ?> params = Collections.singletonMap("userId", string);
		return jdbc.update(DELETE_BY_ID, params);
	}
	
	public int updateProfileImage(Account account) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(account);
		return jdbc.update(UPDATE_XP, params);
	}
	
	public String selectNickname(String string) {
		Map<String, ?> params = Collections.singletonMap("userId", string);
		return jdbc.query(SELECT_BY_ID, params, rowMapper).get(0).getNickname();
	}
	
	public Account selectById(String string) {
		Map<String, ?> params = Collections.singletonMap("userId", string);
		return jdbc.query(SELECT_BY_ID, params, rowMapper).get(0);
	}
	
	public List<Integer> selectAllClearedQuiz(String string) {
		Map<String, ?> params = Collections.singletonMap("userId", string);
		JSONArray jsonArray = jdbc.query(SELECT_BY_ID, params, rowMapper).get(0).getCleared_quiz();
		String jsonString = jsonArray.toString().substring(1, jsonArray.toString().length() - 1);
		if (jsonString.length() == 0) {
			return new ArrayList<>();
		}
		List<String> clearedQuiz = new ArrayList<String>(Arrays.asList(jsonString.split(" ")));
		List<Integer> clearedQuizNums = clearedQuiz.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
		return clearedQuizNums;
	}
	
	public int insertClearedQuiz(int quizNum, String string) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Integer> clearedQuizList = selectAllClearedQuiz(string);
		clearedQuizList.add(quizNum);
		MapSqlParameterSource params;
		try {
			params = new MapSqlParameterSource().addValue("clearedQuiz", objectMapper.writeValueAsString(clearedQuizList)).addValue("userId", string);
			return jdbc.update(UPDATE_CLEARED_QUIZ, params);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public String regBcrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
}
