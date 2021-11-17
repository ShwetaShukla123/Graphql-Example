package com.shweta.graphql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shweta.graphql.model.Book;
import com.shweta.graphql.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>> {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> get(DataFetchingEnvironment environment) throws Exception {
		
		return bookRepository.findAll();
	}

}
