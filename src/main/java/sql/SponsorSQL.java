package sql;

/**
 * Created by LulzimG on 06/01/17.
 */
public class SponsorSQL {
    public static final String GET_ALL_SPONSORS = "SELECT s.* FROM sponsors s INNER JOIN projects p ON s.project_id = p.id AND p.active = 1 WHERE s.active=1";
    public static final String GET_PROJECT_SPONSORS ="SELECT s.* FROM sponsors s INNER JOIN projects p ON s.project_id = p.id AND p.active = 1 WHERE s.active=1 AND p.id=?";
    public static final String GET_SPONSOR ="SELECT s.* FROM sponsors s INNER JOIN projects p ON s.project_id = p.id AND p.active = 1 WHERE s.active=1 AND s.id=?";
    public static final String ADD_SPONSOR ="INSERT INTO sponsors VALUES (:id,:name,:amount,:comment,:project_id,1)";
    public static final String UPDATE_SPONSOR ="UPDATE sponsors SET name=:name,amount=:amount,comment=:comment,project_id=:project_id WHERE id=:id";
    public static final String DELETE_SPONSOR ="UPDATE sponsors SET active=0 WHERE id=:id";
    public static final String INSERT_BATCH = "INSERT INTO sponsors VALUES (?,?,?,?,?,?)";
}
