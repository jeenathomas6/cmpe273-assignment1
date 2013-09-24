package edu.sjsu.cmpe.librarydemo.dto;

import edu.sjsu.cmpe.librarydemo.domain.*;
import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;

@JsonPropertyOrder(alphabetic=false)
public class ReviewsDto extends LinksDto
{
	private ArrayList<Review> reviews=new ArrayList<Review>();
	
	
	public ReviewsDto(ArrayList<Review> reviews)
	{
		super();
		this.reviews=reviews;
	}
	
	public void setReviews(ArrayList<Review> reviews)
	{
		this.reviews=reviews;
	}
	
	public ArrayList<Review> getReviews()
	{
		return this.reviews;
	}
}