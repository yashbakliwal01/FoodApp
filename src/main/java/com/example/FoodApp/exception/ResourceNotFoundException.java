package com.example.FoodApp.exception;

public class ResourceNotFoundException extends RuntimeException{

	  private final String message;
	  public ResourceNotFoundException(String message) {
	        super(message);
	        this.message = message;
	    }

	  public String getMessage() {
	        String message = "Food item not found";
		//	return message;
			return message;
	    }
}
