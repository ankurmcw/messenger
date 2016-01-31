package com.ankur.messenger.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ankur.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException e) {
		ErrorMessage message = new ErrorMessage();
		message.setDocumentationLink("http://localhost:8080/messenger");
		message.setErrorCode(1_1);
		message.setErrorMessage(e.getMessage());
		return Response.status(Status.NOT_FOUND).entity(message).build();
	}

}
