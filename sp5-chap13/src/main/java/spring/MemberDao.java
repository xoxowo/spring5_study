package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectByEmail(String email) { // query()메서드를 이용해 쿼리 실행 
		List<Member> results = jdbcTemplate.query(
			"select*from MEMBER where EMAIL = ?", 
			new RowMapper<Member>() {
				@Override
				public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
					Member member = new Member (
						rs.getString("EMAIL"),
						rs.getString("PASSWORD"),
						rs.getString("NAME"),
						rs.getTimestamp("REGDATE").toLocalDateTime());
					member.setId(rs.getLong("ID"));
					return member;
				}
			}, email);

			/* *람다 표현식
			List<Member> results = jdbcTemplate.query
			"select*from MEMBER where EMAIL = ?",
			(ResultSet rs, int rowNum) -> {
				Member member = new Member (
					rs.getString("EMAIL"),
					rs.getString("PASSWORD"),
					rs.getString("NAME"),
					rs.getTimestamp("REGADATE").toLocalDateTime());	
				member.setId(rs.getLong("ID"));
				return member;
			},
			email);
				*/
		// query() 메서드는 실행한 결과가 존재하지 않으면 길이가 0 인 list를 리턴하므로 
		// list가 비어있는지 여부로 결과를 확인 할 수 있다.
		return results.isEmpty() ? null : results.get(0); 
	}
	public void insert(final Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
					"insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE)" + "values (?, ?, ?, ?)", new String[] {"ID"});
					// 인덱스 파라미터 값 설정
					pstmt.setString(1,member.getEmail());
					pstmt.setString(2,member.getPassword());
					pstmt.setString(3,member.getName());
					pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
					// 생성한 PreparedStatement 객체 리턴
					return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());	// longValue() 메서드로 키를 long 타입으로 변환
	}

	public void update(Member member) {
		jdbcTemplate.update("update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?", 
							member.getName(), member.getPassword(), member.getEmail());
	}

	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER",
				(ResultSet rs, int rowNum) -> {
					Member member = new Member(
							rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getString("NAME"),
							rs.getTimestamp("REGDATE").toLocalDateTime());
					member.setId(rs.getLong("ID"));
					return member;
				});
		return results;
	}

	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from MEMBER", Integer.class);
		return count;
	}


}