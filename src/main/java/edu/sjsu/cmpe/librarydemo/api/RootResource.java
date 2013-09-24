package edu.sjsu.cmpe.librarydemo.api;

import javax.ws.rs.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.yammer.metrics.annotation.*;
import edu.sjsu.cmpe.librarydemo.dto.*;

@Path("/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RootResource
{
	public RootResource()
	{
		
	}
	
	@GET
	@Timed(name="get-root")
	public Response getRoot()
	{
		LinksDto links=new LinksDto();
		LinkDto link=new LinkDto("create-book","/books","POST");
		links.addLink(link);
		//return Response.ok(links).build();
		return Response.status(200).entity(links).build();
	}
}