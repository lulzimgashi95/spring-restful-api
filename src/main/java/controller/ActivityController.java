package controller;

import model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.Activity.ActivityService;
import utilities.MessageGenerator;
import validation.ActivityValidator;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LulzimG on 01/01/17.
 */
@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json"})
    public List<Activity> getAllActivities(@RequestParam(value = "memberId", required = false) String memberId, @RequestParam(value = "projectId", required = false) String projectId) {
        if (memberId != null && projectId == null) {
            return activityService.getMemberActivities(memberId);
        } else if (memberId == null && projectId != null) {
            return activityService.getAllActivities(projectId);
        } else if (memberId == null && projectId == null) {
            return activityService.getAllActivities(null);
        } else {
            return activityService.getActivitiesForPandM(projectId, memberId);
        }
    }

    @RequestMapping(value = "/{activityId}", method = RequestMethod.GET, produces = {"application/json"})
    public Activity getActivity(@PathVariable String activityId) {
        Activity activity = activityService.getActivity(activityId);
        return activity;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> addActivity(@RequestBody @Valid Activity activity, Errors errors) {
        if (errors.hasErrors()) {
            String message = MessageGenerator.generateError(errors.getFieldErrors());
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } else {
            String result = activityService.addActivity(activity);
            String message = MessageGenerator.stringToMsg(result);
            if (result.equals("Done")) {
                return new ResponseEntity<String>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> updateActivity(@RequestBody @Valid Activity activity, Errors errors) {
        if (errors.hasErrors()) {
            String message = MessageGenerator.generateError(errors.getFieldErrors());
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } else {
            String result = activityService.updateActivity(activity);
            String message = MessageGenerator.stringToMsg(result);
            if (result.equals("Done")) {
                return new ResponseEntity<String>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> deleteActivity(@RequestBody Activity activity) {
        String result = activityService.deleteActivity(activity);
        String message = MessageGenerator.stringToMsg(result);
        if (result.equals("Done")) {
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @InitBinder("activity")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ActivityValidator());
    }
}
