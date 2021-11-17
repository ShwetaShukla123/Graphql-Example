package com.shweta.graphql.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shweta.graphql.service.GraphqlService;

import graphql.ExecutionResult;

@RequestMapping(path = "/books")
@RestController
public class BookResource {
	
	@Autowired
	private GraphqlService graphqlService;

	@PostMapping
	public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
		ExecutionResult result = graphqlService.getGraphQL().execute(query);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
