package sql;

/**
 * Created by LulzimG on 06/01/17.
 */
public class MemberSQL {
    public static final String GET_ALL_MEMBERS = "SELECT m.* FROM members m INNER JOIN projects p ON m.project_id=p.id AND p.active=1 WHERE m.active=1";
    public static final String GET_ALL_PROJECT_MEMBERS = "SELECT m.* FROM members m INNER JOIN projects p ON m.project_id=p.id AND p.active=1  WHERE m.active=1 AND m.project_id=?";
    public static final String GET_MEMBER = "SELECT m.* FROM members m INNER JOIN projects p ON m.project_id=p.id AND p.active=1  WHERE m.active=1 AND m.id=?";
    public static final String ADD_MEMBER = "INSERT INTO members VALUES (:id,:firstName,:lastName,:startDate,:position,:image,:projectId,1)";
    public static final String UPDATE_MEMBER = "UPDATE members SET first_name=:firstName,last_name=:lastName,start_date=:startDate,position=:position,image=:image,project_id=:projectId WHERE id=:id";
    public static final String DELETE_MEMBER ="UPDATE members SET active = 0 WHERE id=?";
    public static final String INSERT_BATCH ="INSERT INTO members VALUES (?,?,?,?,?,?,?,?)";
}
