package edu.sjsu.cmpe.librarydemo.dto;

import edu.sjsu.cmpe.librarydemo.domain.*;
import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;

@JsonPropertyOrder(alphabetic=true)
public class AuthorListDto extends LinksDto
{
	private ArrayList<Author> authors=new ArrayList<Author>();
	
	
	public AuthorListDto(ArrayList<Author> authors)
	{
		super();
		this.authors=authors;
	}
	
	public void setAuthors(ArrayList<Author> authors)
	{
		this.authors=authors;
	}
	
	public ArrayList<Author> getAuthors()
	{
		return this.authors;
	}
}