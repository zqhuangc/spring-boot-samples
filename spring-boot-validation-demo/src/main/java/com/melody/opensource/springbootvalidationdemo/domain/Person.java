package com.melody.opensource.springbootvalidationdemo.domain;

import com.melody.opensource.springbootvalidationdemo.bean.validation.constraints.PersonNamePrefix;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 人 领域对象
 *
 * @author zqhuangc
 */
public class Person {

    @NotNull
    @PersonNamePrefix(prefix = "springboot-")
    private String name;

    @Min(value = 0)
    @Max(value = 200
            , message = "{person.age.max.message}"
    )
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
