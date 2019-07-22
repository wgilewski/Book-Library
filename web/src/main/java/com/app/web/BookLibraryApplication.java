package com.app.web;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJSONDoc
public class BookLibraryApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(BookLibraryApplication.class, args);
    }

}
