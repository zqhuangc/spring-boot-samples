package com.melody.opensource.springboot.autoconfiguredemo.configure.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO
 *
 * @author zqhuangc
 */
@ConfigurationProperties("book")
public class Book {

    private String isbn;

    private String name;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
