package sql;

/**
 * Created by LulzimG on 06/01/17.
 */
public class ProjectSQL {
    public static final String GET_ALL_PROJECTS = "SELECT * FROM projects WHERE active = 1";
    public static final String GET_PROJECT ="SELECT * FROM projects WHERE active = 1 AND id = ?";
    public static final String ADD_PROJECT = "INSERT INTO projects VALUES (:id,:name,:description,:start_date,:dead_line,1)";
    public static final String UPDATE_PROJECT ="UPDATE projects SET name=:name,description=:description,start_date=:start_date,dead_line=:dead_line WHERE id =:id";
    public static final String DELETE_PROJECT ="UPDATE projects SET active= 0 WHERE id=?";
}
