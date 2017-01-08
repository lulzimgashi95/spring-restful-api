package controller;

import model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.Project.ProjectService;
import utilities.MessageGenerator;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LulzimG on 29/12/16.
 */
@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<List<Project>> getProjects() {

        List<Project> projects = projectService.getAllProjects();

        return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
    }


    @RequestMapping(value = "/{projectId}", method = RequestMethod.GET, produces = {"application/json"})
    public Project getProject( @PathVariable String projectId) {

        Project project = projectService.getProject(projectId);

        return project;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> addProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = MessageGenerator.generateError(errors.getFieldErrors());
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } else {
            String result = projectService.addProject(project);
            String message = MessageGenerator.stringToMsg(result);
            if (result.equals("Done")) {
                return new ResponseEntity<String>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> updateProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = MessageGenerator.generateError(errors.getFieldErrors());
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } else {
            String result = projectService.updateProject(project);
            String message = MessageGenerator.stringToMsg(result);
            if (result.equals("Done")) {
                return new ResponseEntity<String>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> deleteProject(@RequestBody Project project) {
        String result = projectService.deleteProject(project.getId());
        String message = MessageGenerator.stringToMsg(result);
        if (result.equals("Done")) {
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }
    }

}
