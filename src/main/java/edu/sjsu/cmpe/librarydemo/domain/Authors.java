package edu.sjsu.cmpe.librarydemo.domain;

import java.util.ArrayList;

import edu.sjsu.cmpe.librarydemo.domain.Author;

public class Authors
{
	private static ArrayList<Author> authorList;
	
	static
	{
		authorList=new ArrayList<Author>();
	}
	
	public ArrayList<Author> getAuthors()
	{
		return authorList;
	}
	
	public void setAuthors(ArrayList<Author> authorList)
	{
		this.authorList=authorList;
	}
	
	public Author getAuthorByID(int ID)
	{
		int i;
		boolean res=false;
		for(i=0;i<authorList.size();i++)
		{
			if(Authors.authorList.get(i).getID()==ID)
			{
				res=true;
				break;
			}
		}
		if(res==true)
		{
			return Authors.authorList.get(i);
		}
		else
		{
			return null;
		}
	}
}