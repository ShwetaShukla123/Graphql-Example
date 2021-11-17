package com.shweta.graphql.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
	
	@Id
	private String isbn;
	private String title;
	private String[] authors;
	private String publisher;
	private String publishedDate;
	
}
