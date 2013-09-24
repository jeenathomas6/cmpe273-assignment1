package edu.sjsu.cmpe.librarydemo.domain;

import java.util.ArrayList;
import edu.sjsu.cmpe.librarydemo.domain.Book;


public class Books
{
	private static ArrayList<Book> bookList;
	static
	{
		bookList=new ArrayList<Book>();
	}
	
	public ArrayList<Book> getBooks()
	{
		return bookList;
	}
	
	public void setBooks(ArrayList<Book> bookInfo)
	{
		Books.bookList=bookInfo;
	}
	
	public Book getBookByISBN(int ISBN)
	{
		int i;
		boolean isPresent=false;
		for(i=0;i<bookList.size();i++)
		{
			if(bookList.get(i).getISBN()==ISBN)
			{
				isPresent=true;
				break;
			}
		}
		if(isPresent==true)
		{
		   return bookList.get(i);
		}
		else
		{
			return null;
		}
	}
	
	public int removeBookByISBN(int ISBN)
	{
		int i;
		int result=0;
		try{
		for(i=0;i<bookList.size();i++)
		{
			if(bookList.get(i).getISBN()==ISBN)
			{
				result=1;
				break;
			}
		}
		
		if(result==1)
		{
			bookList.remove(bookList.get(i));
			return 1;
		}
		else
		{
			return -1;
		}
		}
		catch(Exception e)
		{
			return -1;
		}
		
		
	}
		
	
}