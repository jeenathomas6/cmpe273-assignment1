package edu.sjsu.cmpe.librarydemo.dto;

import java.util.List;
import java.util.ArrayList;

public class LinksDto
{
	private List<LinkDto> links=new ArrayList<LinkDto>();
	
	public void addLink(LinkDto link)
	{
		links.add(link);
	}
	
	public List<LinkDto> getLinks()
	{
		return this.links;
	}
	
	public void setLinks(List<LinkDto> links)
	{
		this.links=links;
	}
	
	
}