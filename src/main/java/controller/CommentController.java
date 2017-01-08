package controller;

import model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.Comment.CommentService;
import utilities.MessageGenerator;
import validation.CommentValidator;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LulzimG on 01/01/17.
 */
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json"})
    public List<Comment> getAllComments(@RequestParam(value = "projectId", required = false) String projectId) {
        List<Comment> comments = commentService.getProjectComments(projectId);
        return comments;
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET, produces = {"application/json"})
    public Comment getComment(@PathVariable String commentId) {
        Comment comment = commentService.getComment(commentId);

        return comment;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> addComment(@RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            String message = MessageGenerator.generateError(errors.getFieldErrors());
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } else {
            String result = commentService.addComment(comment);
            String message = MessageGenerator.stringToMsg(result);
            if (result.equals("Done")) {
                return new ResponseEntity<String>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> updateComment(@RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            String message = MessageGenerator.generateError(errors.getFieldErrors());
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } else {
            String result = commentService.updateComment(comment);
            String message = MessageGenerator.stringToMsg(result);
            if (result.equals("Done")) {
                return new ResponseEntity<String>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> deleteComment(@RequestBody Comment comment) {
        String result = commentService.deleteComment(comment);
        String message = MessageGenerator.stringToMsg(result);
        if (result.equals("Done")) {
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @InitBinder("comment")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new CommentValidator());
    }

}
