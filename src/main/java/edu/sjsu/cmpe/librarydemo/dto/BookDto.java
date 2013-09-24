package edu.sjsu.cmpe.librarydemo.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.sjsu.cmpe.librarydemo.domain.Book;
import java.util.ArrayList;
import edu.sjsu.cmpe.librarydemo.domain.*;


@JsonPropertyOrder(alphabetic = true)
public class BookDto extends LinksDto
{
	private Book book;
	private AuthorDto authors;
	
	
	public BookDto(Book book,AuthorDto authors)
	{
		super();
		this.book=book;
		this.authors=authors;
		this.book.getAuthors().clear();
		this.book.getAuthors().add(authors);
	}
	
	public Book getBook()
	{
		return book;
	}
	
	
	
	
	
}