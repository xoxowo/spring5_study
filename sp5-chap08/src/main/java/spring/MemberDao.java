package spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query("select*from MEMBER where EMAIL = ?", 
													new RowMapper<Member>() {
														@Override
														public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
															Member member = new Member (
																rs.getString("EMAIL"),
																rs.getString("PASSWORD"),
																rs.getString("NAME"),
																rs.getTimestamp("REGADATE").toLocalDateTime());
															member.setId(rs.getLong("ID"));
															return member;
														}
													}, email);

		return results.isEmpty() ? null : results.get(0);
	}

	public void insert(Member member) {
	}

	public void update(Member member) {
	}

}