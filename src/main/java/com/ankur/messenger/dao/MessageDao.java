package com.ankur.messenger.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ankur.messenger.database.DatabaseClass;
import com.ankur.messenger.exceptions.DataNotFoundException;
import com.ankur.messenger.model.Comment;
import com.ankur.messenger.model.Message;

public class MessageDao {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public List<Message> getMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public List<Message> getMessagesForYear(int year) {
		List<Message> messageList = new ArrayList<>();
		for (Message m : getMessages()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(m.getDate());
			if (cal.get(Calendar.YEAR) == year) {
				messageList.add(m);
			}
		}
		return messageList;
	}

	public List<Message> getMessagesWithPagination(int start, int size) {
		return this.getMessages().subList(start, size);
	}

	public Message getMessage(long messageId) {
		Message message = messages.get(messageId);
		if(message == null) {
			throw new DataNotFoundException("No message found with message Id " + messageId);
		}
		return message;
	}

	public Message addMessage(Message message) {
		message.setDate(new Date());
		message.setComments(new HashMap<Long, Comment>());
		long messageId = messages.size() + 1;
		message.setMessageId(messageId);
		messages.put(messageId, message);
		return message;
	}

	public Message updateMessage(long messageId, Message message) {
		message.setMessageId(messageId);
		message.setDate(new Date());
		messages.put(message.getMessageId(), message);
		return message;
	}

	public void deleteMessage(long messageId) {
		messages.remove(messageId);
	}
}
