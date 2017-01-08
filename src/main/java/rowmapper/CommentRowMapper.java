package rowmapper;

import model.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by LulzimG on 29/12/16.
 */
public class CommentRowMapper implements RowMapper<Comment> {
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getString("id"));
        comment.setDate(resultSet.getDate("date"));
        comment.setComment(resultSet.getString("comment"));
        comment.setProjectId(resultSet.getString("project_id"));

        return comment;
    }
}
