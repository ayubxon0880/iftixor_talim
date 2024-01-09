package uz.iftixortalim.crmspring.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {
    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                // at least 8 characters
                new LengthRule(4, 16),

                // at least one upper-case character
//                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // at least one lower-case character
//                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // at least one digit character
//                new CharacterRule(EnglishCharacterData.Digit, 1),

                // at least one symbol (special character)
//                new CharacterRule(EnglishCharacterData.Special, 1),

                // no whitespace
                new WhitespaceRule()

        ));
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);

        String messageTemplate = String.join(",", messages);
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
