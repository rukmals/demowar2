package com.example.demowar2;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class BookService {

    DummyBook dummyBook = new DummyBook();


    public DataFetcher getBooks(){
        return env-> dummyBook.getAllBooks() ;

    }

}
