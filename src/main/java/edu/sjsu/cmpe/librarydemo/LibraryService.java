package edu.sjsu.cmpe.librarydemo;

import edu.sjsu.cmpe.librarydemo.config.LibraryServiceConfiguration;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import edu.sjsu.cmpe.librarydemo.api.*;

public class LibraryService extends Service<LibraryServiceConfiguration>
{
	public static void main(String[] args) throws Exception
	{
		new LibraryService().run(args);
	}
	
	@Override
	public void initialize(Bootstrap<LibraryServiceConfiguration> bootstrap)
	{
		bootstrap.setName("library-service");
	}
	
	@Override
	public void run(LibraryServiceConfiguration conf,Environment env)
	{
		env.addResource(RootResource.class);
		env.addResource(BookResource.class);
	}
}