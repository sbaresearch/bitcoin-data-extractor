package thesis.exception;

/**
 * @author Alexei
 */
public abstract class BaseServiceException extends Exception {


    private String developerMessage;

    public BaseServiceException(String userMessage, String developerMessage, Throwable cause) {
        super(userMessage, cause);
        this.developerMessage = developerMessage;
    }

    public BaseServiceException(String userMessage, String developerMessage) {
        super(userMessage);
        this.developerMessage = developerMessage;
    }

    public BaseServiceException(String message, Throwable cause) {
        super(message, cause);
        this.developerMessage = cause.getMessage();
    }

    public BaseServiceException(String message) {
        super(message);
        this.developerMessage = message;
    }

    public BaseServiceException(Throwable cause) {
        super(cause);
        this.developerMessage = cause.getMessage();
    }

    public int getErrorId() {
        return this.errorId();
    }

    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    public String getUserMessage() {
        return this.getMessage();
    }

    protected abstract int errorId();

}
