package kr.or.goldenpaper.userqq.dao;

import static kr.or.goldenpaper.userqq.dao.CommunitySqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.goldenpaper.userqq.dto.Account;
import kr.or.goldenpaper.userqq.dto.Community;

@Repository
public class CommunityDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private static RowMapper<Community> rowMapper = BeanPropertyRowMapper.newInstance(Community.class);
	
	public CommunityDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("community");
	}
	
	public List<Community> selectAllCommunity() {
		return jdbc.query(SELECT_ALL_COMMUNITY, Collections.emptyMap(), rowMapper);
	}
	
	public List<Community> selectCommunityByKeyword() {
		return jdbc.query(SELECT_BY_KEYWORD, Collections.emptyMap(), rowMapper);
	}
	
	public int insert(Community community) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(community);
		return insertAction.execute(params);
	}
	
	public List<Community> selectLast() {
		return jdbc.query(SELECT_LAST, Collections.emptyMap(), rowMapper);
	}
	
	public List<Community> getTen(int start) {
		Map<String, ?> params = Collections.singletonMap("start", start);
		return jdbc.query(SELECT_TEN, params, rowMapper);
	}
	
	public int getCommunityCount() {
		return (int)jdbc.queryForObject(COUNT_ALL, Collections.emptyMap(), Integer.class);
	}
}