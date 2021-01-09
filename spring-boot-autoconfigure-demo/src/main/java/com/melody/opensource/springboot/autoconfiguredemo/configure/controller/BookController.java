package com.melody.opensource.springboot.autoconfiguredemo.configure.controller;

import com.melody.opensource.springboot.autoconfiguredemo.configure.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author zqhuangc
 */
@RestController
@EnableConfigurationProperties(Book.class)
public class BookController {

    @Autowired
    private Book book;

    @GetMapping("/book")
    public Book book() {
        return book;
    }
}
