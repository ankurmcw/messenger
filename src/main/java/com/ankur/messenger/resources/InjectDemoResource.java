package com.ankur.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("annotaions")
	public String getParamsUsingAnnotaions(@MatrixParam("param") String param,
			@HeaderParam("customheader") String customHeader, @CookieParam("name") String cookieValue) {
		return "Matrix Param: " + param + "\nHeader Param: " + customHeader + "\nCookie Param: " + cookieValue;
	}

	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders header) {
		return "AbsolutePath: " + uriInfo.getAbsolutePath().toString() + "\nCookies: " + header.getCookies().toString();
	}
}
