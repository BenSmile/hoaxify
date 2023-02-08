package cd.bensmile.hoaxify.validators;

import cd.bensmile.hoaxify.models.User;
import cd.bensmile.hoaxify.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> userInDB = userRepository.findByUsername(value);
        if (userInDB.isPresent()) {
            return false;
        }
        return true;
    }
}
