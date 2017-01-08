package sql;

/**
 * Created by LulzimG on 06/01/17.
 */
public class ActivitySQL {
    public static final String GET_ALL_ACTIVITIES = "SELECT a.* FROM activities a INNER JOIN members m ON a.member_id=m.id AND m.active=1 INNER JOIN projects p ON m.project_id=p.id AND p.active =1 WHERE a.active=1";
    public static final String GET_PROJECT_ACTIVITIES = "SELECT a.* FROM activities a INNER JOIN members m ON a.member_id=m.id AND m.active=1 INNER JOIN projects p ON m.project_id=p.id AND p.active =1 WHERE a.active=1 AND p.id=?";
    public static final String GET_MEMBER_ACTIVITIES = "SELECT a.* FROM activities a INNER JOIN members m ON a.member_id=m.id AND m.active=1 INNER JOIN projects p ON m.project_id=p.id AND p.active =1 WHERE a.active=1 AND m.id=?";
    public static final String GET_PROJECT_AND_MEMBER_ACTIVITES = "SELECT a.* FROM activities a INNER JOIN members m ON a.member_id=m.id AND m.active=1 INNER JOIN projects p ON m.project_id=p.id AND p.active =1 WHERE a.active=1 AND p.id =? AND m.id=?";
    public static final String GET_ACTIVITY = "SELECT a.* FROM activities a INNER JOIN members m ON a.member_id=m.id AND m.active=1 INNER JOIN projects p ON m.project_id=p.id AND p.active =1 WHERE a.active=1 AND a.id=?";
    public static final String ADD_ACTIVITY ="INSERT INTO activities VALUES (:id,:name,:details,:member_id,1);";
    public static final String UPDATE_ACTIVITY="UPDATE activities SET name=:name,details=:details,member_id=:member_id WHERE id=:id;";
    public static final String DELETE_ACTIVITY="UPDATE activities SET active=0 WHERE id=?;";
    public static final String INSERT_BATCH="INSERT INTO activities VALUES (?,?,?,?,?)";
}
