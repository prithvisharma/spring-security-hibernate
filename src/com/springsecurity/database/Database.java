package com.springsecurity.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.springsecurity.model.User;

@Component
public class Database {

	// connecting Database class with the bean which we created in the srvlet.xml
	private NamedParameterJdbcTemplate jdbc;

	@Autowired // auto wired is used for DataSource which is send as a parameter
	public void setDataSource(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int insertUser(User user) {
		// for preventing sql injections we send data to the query using hash maps
		Map<String, Object> map = new HashMap<>();
		map.put("name", user.getName());
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		map.put("role", user.getRole());

		String sql = "insert into user(name,username,password,role) values(:name,:username,:password,:role)";

		return jdbc.update(sql, map);
	}

	public User loadUserByUsername(String username) {
		MapSqlParameterSource param  =new MapSqlParameterSource();
		param.addValue("username", username);
		String sql="select * from user where username=:username";
		User user = jdbc.queryForObject(sql, param, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user =new User();
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				return user;
			}
			
		});
		return user;
	}
}
