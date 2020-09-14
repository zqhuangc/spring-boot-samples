package com.melody.opensource.springbootvalidationdemo.springboot;

import org.apache.commons.validator.routines.IntegerValidator;

import java.util.Locale;

/**
 * Apache commons-validator Main
 *
 * @author zqhuangc
 */
public class ApacheCommonsValidatorBootstrap {


    public static void main(String[] args) {

        IntegerValidator validator = IntegerValidator.getInstance();

        Integer value = validator.validate("10");

        System.out.println(value);

        value = validator.validate("A");

        System.out.println(value);

        System.out.println(validator.format(100000, Locale.ENGLISH));

    }

}
