package org.icube.ioutracker.util;

import org.icube.ioutracker.repositories.UserRepository;
import org.icube.ioutracker.services.UserService;
import org.icube.ioutracker.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//@Component
public class UniqueNameValidator implements ConstraintValidator<UniqueName,String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueName unique) {
        unique.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("<><> "+s+" >> "+userService);
        return s != null && !userService.existsByName(s);
    }
}
