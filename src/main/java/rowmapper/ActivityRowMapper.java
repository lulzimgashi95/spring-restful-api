package rowmapper;

import model.Activity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by LulzimG on 29/12/16.
 */
public class ActivityRowMapper implements RowMapper<Activity> {
    public Activity mapRow(ResultSet resultSet, int i) throws SQLException {
        Activity activity = new Activity();
        activity.setId(resultSet.getString("id"));
        activity.setName(resultSet.getString("name"));
        activity.setDetails(resultSet.getString("details"));
        activity.setMemberId(resultSet.getString("member_id"));
        return activity;
    }
}
