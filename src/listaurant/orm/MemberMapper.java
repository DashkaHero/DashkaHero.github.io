package listaurant.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import listaurant.model.Member;
import listaurant.model.factory.ModelFactory;

import org.springframework.jdbc.core.RowMapper;

public class MemberMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = ModelFactory.getInstance().createMember(rs.getString("user_id"), null);
		member.setFirstName(rs.getString("first_name"));
		member.setMiddleName(rs.getString("middle_name"));
		member.setLastName(rs.getString("last_name"));

		return member;
	}

}
