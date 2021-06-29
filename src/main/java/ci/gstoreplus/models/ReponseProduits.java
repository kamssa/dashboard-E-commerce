package ci.gstoreplus.models;

import java.util.List;

public class ReponseProduits<T,u,x> {
	private int status;
	
	private List<String> messages;
	private T body;
	private u body1;
	private x body2;

	public ReponseProduits() {
		super();
		
	}
	public ReponseProduits(int status, List<String> messages, T body) {
		super();
		this.status = status;
		this.messages = messages;
		this.body = body;
	}
	
	public ReponseProduits(int status, T body, u body1, x body2) {
		super();
		this.status = status;
		this.body = body;
		this.body1 = body1;
		this.body2 = body2;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	public u getBody1() {
		return body1;
	}
	public void setBody1(u body1) {
		this.body1 = body1;
	}
	public x getBody2() {
		return body2;
	}
	public void setBody2(x body2) {
		this.body2 = body2;
	}

	
}
