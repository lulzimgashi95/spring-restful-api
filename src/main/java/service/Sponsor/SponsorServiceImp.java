package service.Sponsor;

import model.Member;
import model.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import rowmapper.SponsorRowMapper;
import sql.SponsorSQL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by LulzimG on 29/12/16.
 */
@Service("sponsorService")
public class SponsorServiceImp implements SponsorService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Sponsor> getProjectSponsors(String projectId) {
        List<Sponsor> sponsors;
        try {
            if (projectId == null) {
                sponsors = this.jdbcTemplate.query(SponsorSQL.GET_ALL_SPONSORS, new SponsorRowMapper());
            } else {
                sponsors = this.jdbcTemplate.query(SponsorSQL.GET_PROJECT_SPONSORS, new Object[]{projectId}, new SponsorRowMapper());
            }
        } catch (Exception e) {
            return null;
        }
        return sponsors;
    }

    public Sponsor getSponsor(String sponsorId) {
        Sponsor sponsor;
        try {
            sponsor = this.jdbcTemplate.queryForObject(SponsorSQL.GET_SPONSOR,
                    new Object[]{sponsorId}, new SponsorRowMapper());
        } catch (Exception e) {
            return null;
        }
        return sponsor;
    }

    public String addSponsor(Sponsor sponsor) {
        if (sponsor.getId() == null) {
            UUID id = UUID.randomUUID();
            sponsor.setId(id.toString());
        }

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", sponsor.getId())
                .addValue("name", sponsor.getName())
                .addValue("amount", sponsor.getAmount())
                .addValue("comment", sponsor.getComment())
                .addValue("project_id", sponsor.getProjectId());

        try {
            this.namedParameterJdbcTemplate.update(SponsorSQL.ADD_SPONSOR, parameterSource);
        } catch (Exception e) {
            return "Failed :" + e.getMessage();
        }
        return "Done";
    }

    public String updateSponsor(Sponsor sponsor) {
        if (sponsor.getId() == null) {
            return "Bad Sponsor";
        }
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", sponsor.getId())
                .addValue("name", sponsor.getName())
                .addValue("amount", sponsor.getAmount())
                .addValue("comment", sponsor.getComment())
                .addValue("project_id", sponsor.getProjectId());

        try {
            this.namedParameterJdbcTemplate.update(SponsorSQL.UPDATE_SPONSOR, parameterSource);
        } catch (Exception e) {
            return "Failed :" + e.getMessage();
        }

        return "Done";
    }

    public String deleteSponsor(Sponsor sponsor) {
        if (sponsor.getId() == null) {
            return "Bad Sponsor";
        }
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", sponsor.getId())
                .addValue("name", sponsor.getName())
                .addValue("amount", sponsor.getAmount())
                .addValue("comment", sponsor.getComment())
                .addValue("project_id", sponsor.getProjectId());

        try {
            this.namedParameterJdbcTemplate.update(SponsorSQL.DELETE_SPONSOR, parameterSource);
        } catch (Exception e) {
            return "Failed :" + e.getMessage();
        }

        return "Done";
    }

    public void insertBatch(final List<Sponsor> sponsors, final String projectId) {
        for (Sponsor sponsor : sponsors) {
            if (sponsor.getId() == null) {
                UUID id = UUID.randomUUID();
                sponsor.setId(id.toString());
            }
        }
        String sql = SponsorSQL.INSERT_BATCH;
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Sponsor sponsor = sponsors.get(i);
                ps.setString(1, sponsor.getId());
                ps.setString(2, sponsor.getName());
                ps.setDouble(3, sponsor.getAmount());
                ps.setString(4, sponsor.getComment());
                ps.setString(5, projectId);
                ps.setInt(6, 1);


            }

            public int getBatchSize() {
                return sponsors.size();
            }
        });
    }
}
