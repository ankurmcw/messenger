package com.ankur.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.ankur.messenger.dao.MessageDao;
import com.ankur.messenger.model.Message;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	private MessageDao messageDao = new MessageDao();

	@GET
	public List<Message> getAllMessages(@QueryParam("year") int year, @QueryParam("start") int start,
			@QueryParam("size") int size) {
		if (year > 0) {
			return messageDao.getMessagesForYear(year);
		} else if (start >= 0 && size > 0) {
			return messageDao.getMessagesWithPagination(start, size);
		}
		return messageDao.getMessages();
	}

	/*
	 * @GET public List<Message> getAllMessages(@BeanParam MessageFilterBean
	 * filterBean) { if (filterBean.getYear() > 0) { return
	 * messageDao.getMessagesForYear(filterBean.getYear()); } else if
	 * (filterBean.getStart() >= 0 && filterBean.getSize() > 0) { return
	 * messageDao.getMessagesWithPagination(filterBean.getStart(),
	 * filterBean.getSize()); } return messageDao.getMessages(); }
	 */

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId) {
		return messageDao.getMessage(messageId);
	}

	@POST
	public Response postMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageDao.addMessage(message);
		String messageId = String.valueOf(newMessage.getMessageId());
		URI newUri = uriInfo.getAbsolutePathBuilder().path(messageId).build();
		return Response.created(newUri)
				.entity(newMessage)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

	@PUT
	@Path("/{messageId}")
	public Response updateMessage(@PathParam("messageId") long messageId, Message message) {
		return Response.status(Status.ACCEPTED)
				.entity(messageDao.updateMessage(messageId, message))
				.build();
	}

	@DELETE
	@Path("/{messageId}")
	public Response deleteMessage(@PathParam("messageId") long messageId) {
		messageDao.deleteMessage(messageId);
		return Response.status(Status.OK).build();
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentReSource() {
		return new CommentResource();
	}

}
