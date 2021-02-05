package com.springboot.sns.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;


public class Notification {

    //@NonNull
    @JsonProperty("message")
    private String message;
    

	public Notification() {
		super();
	}

	public Notification(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "Notification [message=" + message + "]";
	}
    

	
}
