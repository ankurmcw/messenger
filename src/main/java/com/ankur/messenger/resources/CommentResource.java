package com.ankur.messenger.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ankur.messenger.dao.CommentDao;
import com.ankur.messenger.model.Comment;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentDao commentDao = new CommentDao();

	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return commentDao.getAllComments(messageId);
	}

	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentDao.getComment(messageId, commentId);
	}

	@POST
	public Comment postComment(@PathParam("messageId") long messageId, Comment comment) {
		comment.setCreated(new Date());
		return commentDao.addComment(messageId, comment);
	}

	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId,
			Comment comment) {
		comment.setCommentId(commentId);
		comment.setCreated(new Date());
		return commentDao.updateComment(messageId, commentId, comment);
	}

	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		commentDao.deleteComment(messageId, commentId);
	}

}
