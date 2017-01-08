package controller;

import model.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.Sponsor.SponsorService;
import utilities.MessageGenerator;
import validation.SponsorValidator;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LulzimG on 31/12/16.
 */
@RestController
@RequestMapping("/sponsors")
public class SponsorController {

    @Autowired
    private SponsorService sponsorService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json"})
    public List<Sponsor> getSponsors(@RequestParam(value = "projectId", required = false) String projectId) {
        List<Sponsor> projectSponsors = sponsorService.getProjectSponsors(projectId);
        return projectSponsors;
    }

    @RequestMapping(value = "/{sponsorId}", method = RequestMethod.GET, produces = {"application/json"})
    public Sponsor getSponsor(@PathVariable String sponsorId) {
        Sponsor sponsor = sponsorService.getSponsor(sponsorId);

        return sponsor;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> addSponsor(@RequestBody @Valid Sponsor sponsor, Errors errors) {
        if (errors.hasErrors()) {
            String message = MessageGenerator.generateError(errors.getFieldErrors());
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } else {
            String result = sponsorService.addSponsor(sponsor);
            String message = MessageGenerator.stringToMsg(result);
            if (result.equals("Done")) {
                return new ResponseEntity<String>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> updateSponsor(@RequestBody @Valid Sponsor sponsor, Errors errors) {
        if (errors.hasErrors()) {
            String message = MessageGenerator.generateError(errors.getFieldErrors());
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } else {
            String result = sponsorService.updateSponsor(sponsor);
            String message = MessageGenerator.stringToMsg(result);
            if (result.equals("Done")) {
                return new ResponseEntity<String>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> deleteSponsor(@RequestBody Sponsor sponsor) {
        String result = sponsorService.deleteSponsor(sponsor);
        String message = MessageGenerator.stringToMsg(result);
        if (result.equals("Done")) {
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @InitBinder("sponsor")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new SponsorValidator());
    }
}
