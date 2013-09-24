package edu.sjsu.cmpe.librarydemo.domain;

import java.util.concurrent.atomic.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.validation.constraints.*;

//import com.fasterxml.jackson.annotation.*;
//import org.hibernate.validator.constraints.*;

public class Author
{
	private static AtomicInteger atInt;
	
	static
	{
		atInt=new AtomicInteger();
	}
	
	@JsonProperty("id")
	@JsonInclude(Include.NON_EMPTY)
	private Integer ID;
	
	@JsonInclude(Include.NON_NULL)
	private String name;
	
	//public void createNewAuthor(String name)
	//{
	//	this.ID=atInt.incrementAndGet();
	//	this.name=name;
		
	//}
	
	public Author()
	{
		
	}
	
	public void setID()
	{
		this.ID=atInt.incrementAndGet();
	}
	public Integer getID()
	{
		return ID;
	}
	
	public String getName()
	{
		return name; 
	}
}