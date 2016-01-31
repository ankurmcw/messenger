package com.ankur.messenger.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ankur.messenger.database.DatabaseClass;
import com.ankur.messenger.model.Comment;
import com.ankur.messenger.model.ErrorMessage;
import com.ankur.messenger.model.Message;

public class CommentDao {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId) {
		return new ArrayList<Comment>(messages.get(messageId).getComments().values());
	}

	public Comment getComment(long messageId, long commentId) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setDocumentationLink("");
		errorMessage.setErrorCode(404);
		errorMessage.setErrorMessage("Not Found");
		
		Message message = messages.get(messageId);
		if(message == null) {
			Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
			throw new WebApplicationException(response);
		}
		Comment comment = message.getComments().get(commentId);
		if(comment == null) {
			Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
			throw new NotFoundException(response);
		}
		return comment; 
	}

	public Comment addComment(long messageId, Comment comment) {
		long commentId = messages.get(messageId).getComments().size() + 1;
		comment.setCommentId(commentId);
		messages.get(messageId).getComments().put(commentId, comment);
		return comment;
	}

	public Comment updateComment(long messageId, long commentId, Comment comment) {
		messages.get(messageId).getComments().put(commentId, comment);
		return comment;
	}
	
	public void deleteComment(long messageId, long commentId) {
		messages.get(messageId).getComments().remove(commentId);
	}
}
