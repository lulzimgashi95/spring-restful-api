package rowmapper;

import model.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by LulzimG on 29/12/16.
 */
public class MemberRowMapper implements RowMapper<Member> {
    public Member mapRow(ResultSet resultSet, int i) throws SQLException {
        Member member = new Member();
        member.setId(resultSet.getString("id"));
        member.setFirstName(resultSet.getString("first_name"));
        member.setLastName(resultSet.getString("last_name"));
        member.setStartDate(resultSet.getDate("start_date"));
        member.setPosition(resultSet.getString("position"));
        member.setImage(resultSet.getString("image"));
        member.setProjectId(resultSet.getString("project_id"));
        return member;
    }
}
