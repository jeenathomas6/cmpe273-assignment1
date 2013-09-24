package edu.sjsu.cmpe.librarydemo.dto;

import edu.sjsu.cmpe.librarydemo.domain.*;
import com.fasterxml.jackson.annotation.*;


@JsonPropertyOrder(alphabetic=true)
public class AuthorsDto extends LinksDto
{
	private Author author;
	
	public AuthorsDto(Author author)
	{
		super();
		this.author=author;
	}
	
	
	public Author getAuthor()
	{
		return this.author;
	}
	
	public void setAuthor(Author author)
	{
		this.author=author;
	}
}