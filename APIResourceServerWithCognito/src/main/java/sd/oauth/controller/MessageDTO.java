package sd.oauth.controller;

import java.io.Serializable;

public class MessageDTO  implements Serializable {
	String message;

	public MessageDTO(String message) {
		this.message = message;
	}

	public MessageDTO() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
