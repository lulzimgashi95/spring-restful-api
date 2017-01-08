package validation;

import model.Member;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by LulzimG on 05/01/17.
 */
public class MemberValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Member member = (Member) target;
        if (member.getProjectId() == null) {
            errors.rejectValue("projectId", "", "Project Id should not be null");
        } else if (member.getProjectId().length() != 36) {
            errors.rejectValue("projectId", "", "Project Id should contain 36 characters");
        }
    }
}
