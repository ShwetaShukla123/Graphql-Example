package com.shweta.graphql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shweta.graphql.model.Book;
import com.shweta.graphql.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookDataFetcher implements DataFetcher<Book> {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book get(DataFetchingEnvironment environment) throws Exception {
		String isbn = environment.getArgument("id");
		return bookRepository.findById(isbn).get();
	}

}
