package rowmapper;

import model.Sponsor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by LulzimG on 29/12/16.
 */
public class SponsorRowMapper implements RowMapper<Sponsor> {
    public Sponsor mapRow(ResultSet resultSet, int i) throws SQLException {
        Sponsor sponsor = new Sponsor();
        sponsor.setId(resultSet.getString("id"));
        sponsor.setName(resultSet.getString("name"));
        sponsor.setAmount(resultSet.getDouble("amount"));
        sponsor.setComment(resultSet.getString("comment"));
        sponsor.setProjectId(resultSet.getString("project_id"));

        return sponsor;
    }
}
