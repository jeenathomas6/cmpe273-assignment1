package edu.sjsu.cmpe.librarydemo.domain;

import java.util.concurrent.atomic.AtomicInteger;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Review
{
    private static AtomicInteger atInt;
	
	static
	{
		atInt=new AtomicInteger();
	}
	
	private int ID;
	
	@Min(1)
	@Max(5)
	@NotNull
	private int rating;
	
	@NotNull
	private String comment;
	
	public Review()
	{
		
	}
	
	public void createReview(int rating,String comment)
	{
		setID();
		this.rating=rating;
		this.comment=comment;
	}
	
	public void setID()
	{
		this.ID=atInt.incrementAndGet();
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public int getRating()
	{
		return this.rating;
	}
	public void setRating(int rating)
	{
		this.rating=rating;
	}
	
	public String getComment()
	{
		return this.comment;
	}
	public void setComment(String comment)
	{
		this.comment=comment;
	}
}