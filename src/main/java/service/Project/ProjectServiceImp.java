package service.Project;

import model.Comment;
import model.Member;
import model.Project;
import model.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import rowmapper.ProjectRowMapper;
import service.Comment.CommentService;
import service.Member.MemberService;
import service.Sponsor.SponsorService;
import sql.ProjectSQL;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by LulzimG on 29/12/16.
 */
@Service("projectService")
public class ProjectServiceImp implements ProjectService {

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private MemberService memberService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private CommentService commentService;

    public List<Project> getAllProjects() {
        List<Project> projects;
        try {
            projects = this.jdbcTemplate.query(ProjectSQL.GET_ALL_PROJECTS, new ProjectRowMapper());
        } catch (Exception e) {
            return null;
        }

        List<Member> members = memberService.getProjectMembers(null);
        if (members != null && members.size() > 0) {

            for (Project project : projects) {
                List<Member> memberList = new ArrayList<Member>();

                for (Member member : members) {
                    if (member.getProjectId().equals(project.getId())) {
                        memberList.add(member);
                    }
                }
                project.setMembers(memberList);
            }
        }

        List<Sponsor> sponsors = sponsorService.getProjectSponsors(null);
        if (sponsors != null && sponsors.size() > 0) {

            for (Project project : projects) {
                List<Sponsor> sponsorList = new ArrayList<Sponsor>();

                for (Sponsor sponsor : sponsors) {
                    if (sponsor.getProjectId().equals(project.getId())) {
                        sponsorList.add(sponsor);
                    }
                }
                project.setSponsors(sponsorList);
            }
        }

        List<Comment> comments = commentService.getProjectComments(null);
        if (comments != null && comments.size() > 0) {

            for (Project project : projects) {
                List<Comment> commentList = new ArrayList<Comment>();

                for (Comment comment : comments) {
                    if (comment.getProjectId().equals(project.getId())) {
                        commentList.add(comment);
                    }
                }
                project.setComments(commentList);
            }
        }

        return projects;
    }

    public Project getProject(String projectId) {
        Project project;
        try {
            project = this.jdbcTemplate.queryForObject(ProjectSQL.GET_PROJECT, new Object[]{projectId}, new ProjectRowMapper());
        } catch (Exception e) {
            return null;
        }

        List<Member> members = memberService.getProjectMembers(projectId);

        List<Sponsor> sponsors = sponsorService.getProjectSponsors(projectId);

        List<Comment> comments = commentService.getProjectComments(projectId);


        project.setMembers(members);
        project.setSponsors(sponsors);
        project.setComments(comments);

        return project;
    }

    public String addProject(Project project) {

        if (project.getId() == null) {
            UUID id = UUID.randomUUID();
            project.setId(id.toString());
        }


        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", project.getId())
                .addValue("name", project.getName())
                .addValue("description", project.getDescription())
                .addValue("start_date", project.getStartDate())
                .addValue("dead_line", project.getDeadLine());

        TransactionStatus transaction = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            this.namedParameterJdbcTemplate.update(ProjectSQL.ADD_PROJECT, parameterSource);
            if (project.getMembers() != null && project.getMembers().size() > 0) {
                memberService.insertBatch(project.getMembers(), project.getId());
            }
            if (project.getSponsors() != null && project.getSponsors().size() > 0) {
                sponsorService.insertBatch(project.getSponsors(), project.getId());
            }
            if (project.getComments() != null && project.getComments().size() > 0) {
                commentService.insertBatch(project.getComments(), project.getId());
            }
            platformTransactionManager.commit(transaction);
        } catch (Exception e) {
            platformTransactionManager.rollback(transaction);
            return "Failed :" + e.getMessage();

        }


        return "Done";
    }

    public String updateProject(Project project) {

        if (project.getId() == null) {
            return "Bad Project";
        }

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", project.getId())
                .addValue("name", project.getName())
                .addValue("description", project.getDescription())
                .addValue("start_date", project.getStartDate())
                .addValue("dead_line", project.getDeadLine());

        try {
            this.namedParameterJdbcTemplate.update(ProjectSQL.UPDATE_PROJECT, parameterSource);
        } catch (Exception e) {
            return "Failed : " + e.getMessage();
        }
        return "Done";
    }

    public String deleteProject(String projectId) {

        if (projectId == null) {
            return "Bad Project";
        }
        try {
            this.jdbcTemplate.update(ProjectSQL.DELETE_PROJECT, new Object[]{projectId});
        } catch (Exception e) {
            return "Failed : " + e.getMessage();
        }
        return "Done";
    }
}
