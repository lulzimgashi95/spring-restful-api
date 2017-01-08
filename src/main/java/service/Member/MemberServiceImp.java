package service.Member;

import model.Activity;
import model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import rowmapper.MemberRowMapper;
import service.Activity.ActivityService;
import sql.MemberSQL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by LulzimG on 29/12/16.
 */
@Service("memberService")
public class MemberServiceImp implements MemberService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private ActivityService activityService;

    public List<Member> getProjectMembers(String projectId) {
        List<Member> members;
        try {
            if (projectId == null) {
                members = this.jdbcTemplate.query(MemberSQL.GET_ALL_MEMBERS, new MemberRowMapper());
            } else {
                members = this.jdbcTemplate.query(MemberSQL.GET_ALL_PROJECT_MEMBERS, new Object[]{projectId}, new MemberRowMapper());
            }
        } catch (Exception e) {
            return null;
        }

        List<Activity> activities = activityService.getAllActivities(projectId);

        if (activities != null && activities.size() > 0) {
            for (Member member : members) {
                List<Activity> activityList = new ArrayList<Activity>();
                for (Activity activity : activities) {
                    if (activity.getMemberId().equals(member.getId())) {
                        activityList.add(activity);
                    }
                }
                member.setActivities(activityList);
            }
        }
        return members;
    }

    public Member getMember(String memberId) {
        Member member;
        try {
            member = this.jdbcTemplate.queryForObject(MemberSQL.GET_MEMBER, new Object[]{memberId}, new MemberRowMapper());
        } catch (Exception e) {
            return null;
        }

        List<Activity> memberActivities = activityService.getMemberActivities(memberId);

        member.setActivities(memberActivities);
        return member;
    }

    public String addMember(Member member) {
        if (member.getId() == null) {
            UUID id = UUID.randomUUID();
            member.setId(id.toString());
        }

        if (member.getActivities() != null && member.getActivities().size() > 0) {
            for (Activity activity : member.getActivities()) {
                activity.setMemberId(member.getId());
            }
        }

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", member.getId())
                .addValue("firstName", member.getFirstName())
                .addValue("lastName", member.getLastName())
                .addValue("startDate", member.getStartDate())
                .addValue("position", member.getPosition())
                .addValue("image", member.getImage())
                .addValue("projectId", member.getProjectId());

        TransactionStatus transaction = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            this.namedParameterJdbcTemplate.update(MemberSQL.ADD_MEMBER, parameterSource);
            if (member.getActivities() != null && member.getActivities().size() > 0) {
                activityService.insertBatch(member.getActivities());
            }
            platformTransactionManager.commit(transaction);
        } catch (Exception e) {
            platformTransactionManager.rollback(transaction);
            return "Failed :" + e.getMessage();
        }
        return "Done";


    }

    public String updateMember(Member member) {
        if (member.getId() == null) {
            return "Bad Member";
        }

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", member.getId())
                .addValue("firstName", member.getFirstName())
                .addValue("lastName", member.getLastName())
                .addValue("startDate", member.getStartDate())
                .addValue("position", member.getPosition())
                .addValue("image", member.getImage())
                .addValue("projectId", member.getProjectId());

        try {
            this.namedParameterJdbcTemplate.update(MemberSQL.UPDATE_MEMBER, parameterSource);
        } catch (Exception e) {
            return "Failed :" + e.getMessage();
        }
        return "Done";
    }

    public String deleteMember(Member member) {
        if (member.getId() == null) {
            return "Bad Member";
        }
        try {
            this.jdbcTemplate.update(MemberSQL.DELETE_MEMBER, new Object[]{member.getId()});
        } catch (Exception e) {
            return "Failed :" + e.getMessage();
        }
        return "Done";
    }

    public void insertBatch(final List<Member> members, final String projectId) {
        List<Activity> activities = new ArrayList<Activity>();

        for (Member member : members) {
            if (member.getId() == null) {
                UUID id = UUID.randomUUID();
                member.setId(id.toString());
            }
            if (member.getActivities() != null && member.getActivities().size() > 0) {
                for (Activity activity : member.getActivities()) {
                    activity.setMemberId(member.getId());
                }
                activities.addAll(member.getActivities());
            }
        }

        String sql = MemberSQL.INSERT_BATCH;
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Member member = members.get(i);
                ps.setString(1, member.getId());
                ps.setString(2, member.getFirstName());
                ps.setString(3, member.getLastName());
                ps.setDate(4, new Date(member.getStartDate().getTime()));
                ps.setString(5, member.getPosition());
                ps.setString(6, member.getImage());
                ps.setString(7, projectId);
                ps.setInt(8, 1);

            }

            public int getBatchSize() {
                return members.size();
            }
        });

        if (activities.size() > 0) {
            activityService.insertBatch(activities);
        }
    }
}
