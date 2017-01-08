package controller;

import model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.Member.MemberService;
import utilities.MessageGenerator;
import validation.MemberValidator;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LulzimG on 31/12/16.
 */
@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json"})
    public List<Member> getAllMembers(@RequestParam(value = "projectId", required = false) String projectId) {
        List<Member> projectMembers = memberService.getProjectMembers(projectId);

        return projectMembers;
    }

    @RequestMapping(value = "/{memberId}", method = RequestMethod.GET, produces = {"application/json"})
    public Member getMember(@PathVariable String memberId) {
        Member member = memberService.getMember(memberId);

        return member;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> addMember(@RequestBody @Valid Member member, Errors errors) {
        if (errors.hasErrors()) {
            String message = MessageGenerator.generateError(errors.getFieldErrors());
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } else {
            String result = memberService.addMember(member);
            String message = MessageGenerator.stringToMsg(result);
            if (result.equals("Done")) {
                return new ResponseEntity<String>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> updateMember(@RequestBody @Valid Member member, Errors errors) {
        if (errors.hasErrors()) {
            String message = MessageGenerator.generateError(errors.getFieldErrors());
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        } else {
            String result = memberService.updateMember(member);
            String message = MessageGenerator.stringToMsg(result);
            if (result.equals("Done")) {
                return new ResponseEntity<String>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> deleteMember(@RequestBody Member member) {
        String result = memberService.deleteMember(member);
        String message = MessageGenerator.stringToMsg(result);
        if (result.equals("Done")) {
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @InitBinder("member")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new MemberValidator());
    }
}
