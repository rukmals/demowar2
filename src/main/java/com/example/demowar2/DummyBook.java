package com.example.demowar2;

import java.util.Arrays;
import java.util.List;

public class DummyBook {


    List<Book> books = Arrays.asList(
            new Book("1", "A",12),
            new Book("2","B",13)
    );

    public List<Book> getAllBooks(){
        return books;
    }
 }
