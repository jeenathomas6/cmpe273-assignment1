package edu.sjsu.cmpe.librarydemo.domain;

import edu.sjsu.cmpe.librarydemo.domain.Author;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.*;

public class Book
{
	@JsonIgnore
	private ArrayList<Author> authList=new ArrayList<Author>();
	private static AtomicInteger count;
	
	static
	{
		count=new AtomicInteger();
	}
	
	@JsonProperty("isbn")
	private int ISBN;
	
	@JsonProperty
	@NotNull
	private String title;
	
	@JsonProperty("publication-date")
	@NotNull
	private String publicationDate;
	
	@JsonProperty
	private String language;
	
	@JsonProperty("num-pages")
	private int numberOfPages;
	
	@JsonProperty
	private ArrayList<Author> authors;
	
	@JsonProperty
	private ArrayList<Review> reviews=new ArrayList<Review>();
	
	private enum Status
	{
		available,checkedout,inqueue,lost;
		
	}
	
	@JsonProperty
	private Status status;
	
	public void CreateNewBook(String title,String publicationDate,String language, int numberOfPages,ArrayList<Review> reviewList,ArrayList<Author> authorList)
	{
		this.ISBN=count.incrementAndGet();
		this.title=title;
		this.publicationDate=publicationDate;
		this.language=language;
		this.numberOfPages=numberOfPages;
		if(reviewList!=null)
		{
			for(int j=0;j<reviewList.size();j++)
			{
				reviewList.get(j).setID();
			}
		}
		this.reviews=reviewList;
		
		for(int i=0;i<authorList.size();i++)
		{
			authorList.get(i).setID();
		}
		this.authors=authorList;
	
		for(int i=0;i<authorList.size();i++)
		{
			this.authList.add(authorList.get(i));
		}
		this.status=Status.available;
	    
	}
	
	public Book()
	{
		    
	}
	
	
	public int getISBN()
	{
		return this.ISBN;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getPublicationDate()
	{
		return this.publicationDate;
	}
	
	public ArrayList<Author> getAuthors()
	{
		return this.authors;
	}
	
	public void addAuthor(Author author)
	{
		this.authors.add(author);
	}
	
	public int getNumberOfPages()
	{
		return this.numberOfPages;
	}
	
	public String getLanguage()
	{
		return this.language;
	}
	
	
	public void setStatus(String status)
	{
		this.status=Status.valueOf(status);
	}
	
	public ArrayList<Review> getReviews()
	{
		return this.reviews;
	}
	
	@JsonIgnore
	public ArrayList<Author> getAuthList()
	{
		return this.authList;
	}
	
	public Review getReviewsForBook(int ID)
	{
		int i;
		boolean res=false;
		for(i=0;i<this.reviews.size();i++)
		{
			if(this.reviews.get(i).getID()==ID)
			{
				res=true;
				break;
			}
		}
		if(res==true)
		{
			return this.reviews.get(i);
		}
		else
		{
			return null;
		}
	}
	
	
	public Author getAuthorByID(int ID)
	{
		int i;
		boolean res=false;
		for(i=0;i<this.authors.size();i++)
		{
			if(this.authors.get(i).getID()==ID)
			{
				res=true;
				break;
			}
		}
		if(res==true)
		{
			return this.authors.get(i);
		}
		else
		{
			return null;
		}
	}

	
	
}