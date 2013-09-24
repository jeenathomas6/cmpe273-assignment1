package edu.sjsu.cmpe.librarydemo.domain;

import java.util.ArrayList;

public class Reviews
{
	private static ArrayList<Review> reviews;
	
	static
	{
		reviews=new ArrayList<Review>();
	}
	
	public void setReviews(ArrayList<Review> reviews)
	{
		Reviews.reviews=reviews;
	}
	
	public ArrayList<Review> getReviews()
	{
		return Reviews.reviews;
	}
	
	public Review getReviewByID(int ID)
	{
		int i;
		boolean checkRes=false;
		for(i=0;i<reviews.size();i++)
		{
			if(reviews.get(i).getID()==ID)
			{
				checkRes=true;
				break;
			}
		}
		if(checkRes==true)
		{
			return reviews.get(i);
		}
		else
		{
			return null;
		}
	}
}