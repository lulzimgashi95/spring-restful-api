package sql;

/**
 * Created by LulzimG on 06/01/17.
 */
public class CommentSQL {
    public static final String GET_ALL_COMMENTS ="SELECT c.* FROM comments c INNER JOIN projects p ON c.project_id=p.id AND p.active=1 WHERE c.active=1";
    public static final String GET_PROJECT_COMMENTS ="SELECT c.* FROM comments c INNER JOIN projects p ON c.project_id=p.id AND p.active=1 WHERE c.active=1 AND p.id=?";
    public static final String GET_COMMENT ="SELECT c.* FROM comments c INNER JOIN projects p ON c.project_id=p.id AND p.active=1 WHERE c.active=1 AND c.id=?";
    public static final String ADD_COMMENT ="INSERT INTO comments VALUES (:id,:comment,:date,:project_id,1)";
    public static final String UPDATE_COMMENT ="UPDATE comments SET comment=:comment,date=:date,project_id=:project_id WHERE id=:id";
    public static final String DELETE_COMMENT ="UPDATE comments SET active=0 WHERE id=:id";
    public static final String INSERT_BATCH ="INSERT INTO comments VALUES (?,?,?,?,?)";
}
