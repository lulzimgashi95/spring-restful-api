package service.Activity;

import model.Activity;
import model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import rowmapper.ActivityRowMapper;
import sql.ActivitySQL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by LulzimG on 29/12/16.
 */
@Service("activityService")
public class ActivityServiceImp implements ActivityService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Activity> getAllActivities(String projectId) {
        List<Activity> activities;
        try {
            if (projectId == null) {
                activities = this.jdbcTemplate.query(ActivitySQL.GET_ALL_ACTIVITIES, new ActivityRowMapper());
            } else {
                activities = this.jdbcTemplate.query(ActivitySQL.GET_PROJECT_ACTIVITIES, new Object[]{projectId}, new ActivityRowMapper());
            }
        } catch (Exception e) {
            return null;
        }
        return activities;
    }

    public List<Activity> getMemberActivities(String memberId) {
        List<Activity> activities;
        try {
            activities = this.jdbcTemplate.query(ActivitySQL.GET_MEMBER_ACTIVITIES, new Object[]{memberId}, new ActivityRowMapper());
        } catch (Exception e) {
            return null;
        }

        return activities;
    }

    public List<Activity> getActivitiesForPandM(String projectId, String memberId) {
        List<Activity> activities;
        try {
            activities = this.jdbcTemplate.query(ActivitySQL.GET_PROJECT_AND_MEMBER_ACTIVITES, new Object[]{projectId, memberId}, new ActivityRowMapper());
        } catch (Exception e) {
            return null;
        }

        return activities;
    }

    public Activity getActivity(String activityId) {
        Activity activity;
        try {
            activity = this.jdbcTemplate.queryForObject(ActivitySQL.GET_ACTIVITY, new Object[]{activityId}, new ActivityRowMapper());
        } catch (Exception e) {
            return null;
        }
        return activity;
    }

    public String addActivity(Activity activity) {
        if (activity.getId() == null) {
            UUID id = UUID.randomUUID();
            activity.setId(id.toString());
        }

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", activity.getId())
                .addValue("name", activity.getName())
                .addValue("details", activity.getDetails())
                .addValue("member_id", activity.getMemberId());

        try {
            this.namedParameterJdbcTemplate.update(ActivitySQL.ADD_ACTIVITY, parameterSource);
        } catch (Exception e) {
            return "Failed :" + e.getMessage();
        }

        return "Done";
    }

    public String updateActivity(Activity activity) {
        if (activity.getId() == null) {
            return "Bad Project";
        }

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", activity.getId())
                .addValue("name", activity.getName())
                .addValue("details", activity.getDetails())
                .addValue("member_id", activity.getMemberId());

        try {
            this.namedParameterJdbcTemplate.update(ActivitySQL.UPDATE_ACTIVITY, parameterSource);
        } catch (Exception e) {
            return "Failed :" + e.getMessage();
        }

        return "Done";
    }

    public String deleteActivity(Activity activity) {
        if (activity.getId() == null) {
            return "Bad Project";
        }
        try {
            this.jdbcTemplate.update(ActivitySQL.DELETE_ACTIVITY, new Object[]{activity.getId()});
        } catch (Exception e) {
            return "Failed :" + e.getMessage();
        }

        return "Done";
    }

    public void insertBatch(final List<Activity> activities) {
        for (Activity activity : activities) {
            if (activity.getId() == null) {
                UUID id = UUID.randomUUID();
                activity.setId(id.toString());
            }
        }

        String sql = ActivitySQL.INSERT_BATCH;
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Activity activity = activities.get(i);
                ps.setString(1, activity.getId());
                ps.setString(2, activity.getName());
                ps.setString(3, activity.getDetails());
                ps.setString(4, activity.getMemberId());
                ps.setInt(5, 1);

            }

            public int getBatchSize() {
                return activities.size();
            }
        });
    }
}
