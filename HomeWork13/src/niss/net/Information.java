package niss.net;

import java.io.Serializable;

public class Information implements Serializable {
	private String fromUser;
	private String toUser;
    private String message;

    public Information(String fromUser, String toUser, String message) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.message = message;
    }

    public String getFromUser() {
        return fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public String getMessage() {
        return message;
    }
}
