package service.Sponsor;

import model.Sponsor;

import java.util.List;

/**
 * Created by LulzimG on 29/12/16.
 */
public interface SponsorService {
    List<Sponsor> getProjectSponsors(String projectId);

    Sponsor getSponsor(String sponsorId);

    String addSponsor(Sponsor sponsor);

    String updateSponsor(Sponsor sponsor);

    String deleteSponsor(Sponsor sponsor);

    void insertBatch(List<Sponsor> sponsors, String id);
}
