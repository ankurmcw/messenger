package com.ankur.messenger.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ankur.messenger.model.ErrorMessage;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable e) {
		ErrorMessage message = new ErrorMessage();
		message.setDocumentationLink("http://localhost:8080/messenger/");
		message.setErrorCode(1_2);
		message.setErrorMessage(e.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(message).build();
	}

}
