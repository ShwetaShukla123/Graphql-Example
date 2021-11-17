package com.shweta.graphql.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.shweta.graphql.model.Book;
import com.shweta.graphql.repository.BookRepository;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphqlService {

	@Autowired
	private BookRepository bookRepository;
	
	@Value("classpath:books.graphql")
	Resource resource;
	
	private GraphQL graphQL;

	@Autowired
	private AllBooksDataFetcher allBooksFetcher;

	@Autowired
	private BookDataFetcher bookFetcher;
	
	//load the schema on startup 
	@PostConstruct
	private void loadSchema() throws IOException {
		//loadDataIntoHSQLDB
		
		loadDataIntoHSQL();
		
		//get the schema
		File file = resource.getFile();
		
		//parse the schema
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
		RuntimeWiring wiring = buildRunTimeWiring();
		GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
		graphQL = GraphQL.newGraphQL(graphQLSchema).build();
	}


	private void loadDataIntoHSQL() {
		Stream.of(new Book("123", "Effective Java",new String[]{"Joshua Bloch"},"Orielly", "June 2001"),
				new Book("124", "Head First Java",new String[]{"Kathy Sierra", "Bert Bates"},"Amazon", "May 2003"),
				new Book("125", "Thinking in Java",new String[]{"Bruce Eckel"},"Amazon", "April 1998"))
		.forEach(book -> bookRepository.save(book));
	}


	private RuntimeWiring buildRunTimeWiring() {
		
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
										.dataFetcher("allBooks", allBooksFetcher)
										.dataFetcher("book", bookFetcher)
				).build();
	}
	
	public GraphQL getGraphQL() {
		return graphQL;
	}
	
}
