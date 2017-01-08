package validation;

import model.Sponsor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by LulzimG on 05/01/17.
 */
public class SponsorValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Sponsor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sponsor sponsor = (Sponsor) target;
        if (sponsor.getProjectId() == null) {
            errors.rejectValue("projectId", "", "Project Id should not be null");
        } else if (sponsor.getProjectId().length() != 36) {
            errors.rejectValue("projectId", "", "Project Id should contain 36 characters");
        }
    }
}