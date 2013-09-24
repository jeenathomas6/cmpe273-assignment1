package edu.sjsu.cmpe.librarydemo.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import javax.validation.*;
import javax.ws.rs.WebApplicationException;

import edu.sjsu.cmpe.librarydemo.dto.*;
import edu.sjsu.cmpe.librarydemo.domain.*;

@Path("/v1/books/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource
{
	private Books bookList;
	private Reviews reviewList;
	private Authors authorList;
	
	public BookResource()
	{
		bookList=new Books();
		reviewList=new Reviews();
		authorList=new Authors();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response InsertBook(@Valid Book newBook)
	{
		newBook.CreateNewBook(newBook.getTitle(),newBook.getPublicationDate(),newBook.getLanguage(),newBook.getNumberOfPages(),newBook.getReviews(),newBook.getAuthors());
		bookList.getBooks().add(newBook);
		
		LinksDto links=new LinksDto();
		LinkDto link1=new LinkDto("view-book","/books/"+newBook.getISBN(),"GET");
		links.addLink(link1);
		LinkDto link2=new LinkDto("update-book","/books/"+newBook.getISBN(),"PUT");
		links.addLink(link2);
		LinkDto link3=new LinkDto("delete-book","/books/"+newBook.getISBN(),"DELETE");
		links.addLink(link3);
		LinkDto link4=new LinkDto("create-review","/books/"+newBook.getISBN()+"/reviews","POST");
		links.addLink(link4);
		//return Response.ok(links).build();
		return Response.status(201).entity(links).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{ISBN}")
	public Response GetBookByISBN(@PathParam("ISBN") int ISBN)
	{
		Book book=bookList.getBookByISBN(ISBN);
	    
        AuthorDto authorResp=new AuthorDto();
		
		for(int i=0;i<book.getAuthList().size();i++)
		{
			authorList.getAuthors().add(book.getAuthList().get(i));
			//book.getAuthList().add(book.getAuthors().get(i));
			LinkDto lnk1=new LinkDto("view-author","/books/"+book.getISBN()+"/authors/"+book.getAuthList().get(i).getID(),"GET");
			authorResp.addLink(lnk1);
			//bookResponse.add(lnk1);
		}
		BookDto bookResponse=new BookDto(book,authorResp);
		
		
		LinkDto link1=new LinkDto("view-book","/books/"+book.getISBN(),"GET");
		bookResponse.addLink(link1);
		LinkDto link2=new LinkDto("update-book","/books/"+book.getISBN(),"PUT");
		bookResponse.addLink(link2);
		LinkDto link3=new LinkDto("delete-book","/books/"+book.getISBN(),"DELETE");
		bookResponse.addLink(link3);
		LinkDto link4=new LinkDto("create-review","/books/"+book.getISBN()+"/reviews","POST");
		bookResponse.addLink(link4);
		if(!(book.getReviews().isEmpty()))
		{
			LinkDto link5=new LinkDto("view-all-reviews","/books/"+book.getISBN()+"/reviews","GET");
			bookResponse.addLink(link5);
		}
		return Response.status(200).entity(bookResponse).build();
	}
	
	@DELETE
	@Path("/{ISBN}")
	public Response deleteBook(@PathParam("ISBN") int ISBN)
	{
		try{
		int res=bookList.removeBookByISBN(ISBN);
		if(res==1)
		{
			LinksDto links=new LinksDto();
			LinkDto link1=new LinkDto("create-book","/books","POST");
			links.addLink(link1);
			return Response.status(200).entity(links).build();
		}
		else
		{
			return Response.noContent().build();
		}
		}
		catch(WebApplicationException e)
		{
			e.printStackTrace();
			return Response.noContent().build();
		}
		
	
	}
	
	
	@PUT
	@Path("/{ISBN}")
	public Response updateBook(@PathParam("ISBN") int ISBN,@QueryParam("status") @DefaultValue("available") String status)
	{
		Book book=bookList.getBookByISBN(ISBN);
		book.setStatus(status.toLowerCase().trim());
		
		LinksDto links=new LinksDto();
		LinkDto link1=new LinkDto("view-book","/books/"+book.getISBN(),"GET");
		links.addLink(link1);
		LinkDto link2=new LinkDto("update-book","/books/"+book.getISBN(),"PUT");
		links.addLink(link2);
		LinkDto link3=new LinkDto("delete-book","/books/"+book.getISBN(),"DELETE");
		links.addLink(link3);
		LinkDto link4=new LinkDto("create-review","/books/"+book.getISBN()+"/reviews","POST");
		links.addLink(link4);
		if(!(book.getReviews().isEmpty()))
		{
			LinkDto link5=new LinkDto("view-all-reviews","/books/"+book.getISBN()+"/reviews","GET");
			links.addLink(link5);
		}
		return Response.status(200).entity(links).build();
	}
	
	@POST
	@Path("/{ISBN}/reviews")
	public Response createReview(@PathParam("ISBN") int ISBN,@Valid Review review)
	{
		review.createReview(review.getRating(), review.getComment());
		reviewList.getReviews().add(review);
		
		Book book=bookList.getBookByISBN(ISBN);
		book.getReviews().add(review);
		
		LinksDto links=new LinksDto();
		LinkDto link1=new LinkDto("view-review","/books/"+book.getISBN()+"/reviews/"+review.getID(),"GET");
		links.addLink(link1);
		return Response.status(201).entity(links).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{ISBN}/reviews/{ID}")
	public Response viewBookReview(@PathParam("ISBN") int ISBN, @PathParam("ID") int ID)
	{
		Book book=bookList.getBookByISBN(ISBN);
		Review review=book.getReviewsForBook(ID);
		
		ReviewDto revResponse=new ReviewDto(review);
		
		LinkDto link1=new LinkDto("view-review","/books/"+book.getISBN()+"/reviews/"+review.getID(),"GET");
		revResponse.addLink(link1);
		
		return Response.status(200).entity(revResponse).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{ISBN}/reviews")
	public Response viewAllReviews(@PathParam("ISBN") int ISBN)
	{
		Book book=bookList.getBookByISBN(ISBN);
		ArrayList<Review> reviews=book.getReviews();
		ReviewsDto revResponse=new ReviewsDto(reviews);
		return Response.status(200).entity(revResponse).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{ISBN}/authors/{ID}")
	public Response viewBookAuthor(@PathParam("ISBN") int ISBN, @PathParam("ID") int ID)
	{
		Book book=bookList.getBookByISBN(ISBN);
		Author author=authorList.getAuthorByID(ID);
		//Author author=book.getAuthorByID(ID);
		AuthorsDto authorResponse=new AuthorsDto(author);
		
		LinkDto link1=new LinkDto("view-author","/books/"+book.getISBN()+"/authors/"+author.getID(),"GET");
		authorResponse.addLink(link1);
		return Response.status(200).entity(authorResponse).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{ISBN}/authors")
	public Response viewAllAuthors(@PathParam("ISBN") int ISBN)
	{
		Book book=bookList.getBookByISBN(ISBN);
		ArrayList<Author> authors=book.getAuthList();
		AuthorListDto authorRes=new AuthorListDto(authors);
		return Response.status(200).entity(authorRes).build();
	}
	
	
	
	
	
	
}