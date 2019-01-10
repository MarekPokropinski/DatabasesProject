package project.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserException extends Exception {
	private static final long serialVersionUID = 7961947031115495375L;
	private String message;

	public UserException(String message) {
		this.message = message;
	}

	public UserException() {
	}

	@Override
	public String getMessage() {
		return message;
	}
}
