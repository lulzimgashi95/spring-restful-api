package service.Comment;

import model.Comment;
import model.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import rowmapper.CommentRowMapper;
import sql.CommentSQL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by LulzimG on 29/12/16.
 */
@Service("commentService")
public class CommentServiceImp implements CommentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Comment> getProjectComments(String projectId) {
        List<Comment> comments;
        try {
            if (projectId == null) {
                comments = this.jdbcTemplate.query(CommentSQL.GET_ALL_COMMENTS, new CommentRowMapper());
            } else {
                comments = this.jdbcTemplate.query(CommentSQL.GET_PROJECT_COMMENTS,
                        new Object[]{projectId}, new CommentRowMapper());
            }
        } catch (Exception e) {
            return null;
        }
        return comments;
    }

    public Comment getComment(String commentId) {
        Comment comment;
        try {
            comment = this.jdbcTemplate.queryForObject(CommentSQL.GET_COMMENT, new Object[]{commentId}, new CommentRowMapper());
        } catch (Exception e) {
            return null;
        }

        return comment;
    }

    public String addComment(Comment comment) {
        if (comment.getId() == null) {
            UUID id = UUID.randomUUID();
            comment.setId(id.toString());
        }

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", comment.getId())
                .addValue("comment", comment.getComment())
                .addValue("date", comment.getDate())
                .addValue("project_id", comment.getProjectId());

        try {
            this.namedParameterJdbcTemplate.update(CommentSQL.ADD_COMMENT, parameterSource);
        } catch (Exception e) {
            return "Failed :" + e.getMessage();
        }

        return "Done";
    }

    public String updateComment(Comment comment) {
        if (comment.getId() == null) {
            return "Bad Project";
        }

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", comment.getId())
                .addValue("comment", comment.getComment())
                .addValue("date", comment.getDate())
                .addValue("project_id", comment.getProjectId());

        try {
            this.namedParameterJdbcTemplate.update(CommentSQL.UPDATE_COMMENT, parameterSource);
        } catch (Exception e) {
            return "Failed :" + e.getMessage();
        }

        return "Done";
    }

    public String deleteComment(Comment comment) {
        if (comment.getId() == null) {
            return "Bad Project";
        }

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", comment.getId())
                .addValue("comment", comment.getComment())
                .addValue("date", comment.getDate())
                .addValue("project_id", comment.getProjectId());

        try {
            this.namedParameterJdbcTemplate.update(CommentSQL.DELETE_COMMENT, parameterSource);
        } catch (Exception e) {
            return "Failed :" + e.getMessage();
        }

        return "Done";
    }

    public void insertBatch(final List<Comment> comments, final String projectId) {
        for (Comment comment : comments) {
            if (comment.getId() == null) {
                UUID id = UUID.randomUUID();
                comment.setId(id.toString());
            }
        }
        String sql = CommentSQL.INSERT_BATCH;
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Comment comment = comments.get(i);
                ps.setString(1, comment.getId());
                ps.setString(2, comment.getComment());
                ps.setDate(3, new Date(comment.getDate().getTime()));
                ps.setString(4, projectId);
                ps.setInt(5, 1);

            }

            public int getBatchSize() {
                return comments.size();
            }
        });
    }
}
