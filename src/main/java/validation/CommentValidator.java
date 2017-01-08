package validation;

import model.Comment;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by LulzimG on 05/01/17.
 */
public class CommentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Comment.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Comment comment = (Comment) target;
        if (comment.getProjectId() == null ) {
            errors.rejectValue("projectId", "", "Project Id should not be null");
        } else if (comment.getProjectId().length() != 36) {
            errors.rejectValue("projectId", "", "Project Id should contain 36 characters");
        }
    }
}
