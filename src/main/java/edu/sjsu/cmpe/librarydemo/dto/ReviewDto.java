package edu.sjsu.cmpe.librarydemo.dto;

import edu.sjsu.cmpe.librarydemo.domain.*;
import com.fasterxml.jackson.annotation.*;


@JsonPropertyOrder(alphabetic=false)
public class ReviewDto extends LinksDto
{
	private Review review;
	
	public ReviewDto(Review review)
	{
		super();
		this.review=review;
	}
	
	
	public Review getReview()
	{
		return this.review;
	}
	
	public void setReview(Review review)
	{
		this.review=review;
	}
}