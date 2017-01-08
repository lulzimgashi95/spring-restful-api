package validation;

import model.Activity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by LulzimG on 05/01/17.
 */
public class ActivityValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Activity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Activity activity = (Activity) target;
        if (activity.getMemberId() == null ) {
            errors.rejectValue("memberId", "", "Member Id Id should not be null");
        } else if (activity.getMemberId().length() != 36) {
            errors.rejectValue("memberId", "", "Member Id should contain 36 characters");
        }
    }
}
