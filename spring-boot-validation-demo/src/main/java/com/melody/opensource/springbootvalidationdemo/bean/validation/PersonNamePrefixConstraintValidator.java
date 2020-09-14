package com.melody.opensource.springbootvalidationdemo.bean.validation;

import com.melody.opensource.springbootvalidationdemo.bean.validation.constraints.PersonNamePrefix;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * {@link PersonNamePrefix} {@link ConstraintValidator}
 *
 * @author zqhuangc
 * @see PersonNamePrefix
 * @see ConstraintValidator
 */
public class PersonNamePrefixConstraintValidator implements ConstraintValidator<PersonNamePrefix, String> {

    private String prefix;

    @Override
    public void initialize(PersonNamePrefix constraintAnnotation) {
        this.prefix = constraintAnnotation.prefix();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {

        if (!name.startsWith(prefix)) {

            context.disableDefaultConstraintViolation();

            ConstraintValidatorContext.ConstraintViolationBuilder builder =
                    context.buildConstraintViolationWithTemplate("人的名称必须以\"" + prefix + "\"起始！");
            builder.addConstraintViolation();

            return false;
        }

        return true;
    }
}
