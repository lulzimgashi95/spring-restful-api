package rowmapper;

import model.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by LulzimG on 29/12/16.
 */
public class ProjectRowMapper implements RowMapper<Project> {
    public Project mapRow(ResultSet resultSet, int i) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getString("id"));
        project.setName(resultSet.getString("name"));
        project.setDescription(resultSet.getString("description"));
        project.setStartDate(resultSet.getDate("start_date"));
        project.setDeadLine(resultSet.getDate("dead_line"));

        return project;
    }
}
