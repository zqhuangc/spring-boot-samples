package com.melody.opensource.springbootvalidationdemo.spring.validator;


import com.melody.opensource.springbootvalidationdemo.domain.Person;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * {@link Person} {@link Validator}
 *
 * @author zqhuangc
 * @see Person
 * @see Validator
 */
public class PersonValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Person person = Person.class.cast(target);

        String name = person.getName(); //名字需要校验

        if (!StringUtils.hasLength(name)) {

            errors.reject("person.name.not.null", "人的名字不能为空");

        }

    }
}
