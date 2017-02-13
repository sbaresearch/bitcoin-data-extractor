package thesis.exception;

/**
 * @author Alexei
 */
public class ServiceException extends BaseServiceException{

    public static final int SERVICE_EXCEPTION = 1;


    public static final int NOT_FOUND_EXCEPTION = 40;
    public static final int ENTITY_NOT_FOUND_EXCEPTION = 41;
    public static final int RESOURCE_NOT_FOUND_EXCEPTION = 42;
    public static final int UPDATE_DENIED_EXCEPTION = 60;
    private static final long serialVersionUID = -3036584752507125881L;


    public ServiceException(String userMessage, String developerMessage, Throwable cause) {
        super(userMessage, developerMessage, cause);
    }

    public ServiceException(String userMessage, String developerMessage) {
        super(userMessage, developerMessage);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    @Override
    protected int errorId() {
        return SERVICE_EXCEPTION;
    }
}
