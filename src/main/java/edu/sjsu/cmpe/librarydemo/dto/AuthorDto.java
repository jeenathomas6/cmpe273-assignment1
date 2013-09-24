package edu.sjsu.cmpe.librarydemo.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.sjsu.cmpe.librarydemo.domain.*;

public class AuthorDto extends Author
{
	private List<LinkDto> authors=new ArrayList<LinkDto>();
	
	public void addLink(LinkDto link)
	{
		authors.add(link);
	}
	
	public List<LinkDto> getLinks()
	{
		return this.authors;
	}
	
	public void setLinks(List<LinkDto> authors)
	{
		this.authors=authors;
	}
}
