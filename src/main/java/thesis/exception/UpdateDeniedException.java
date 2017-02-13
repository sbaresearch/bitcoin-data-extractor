package thesis.exception;

public class UpdateDeniedException extends ServiceException {


    public UpdateDeniedException(String msg) {
        super(msg);
    }

    private static final long serialVersionUID = -7477846998741507911L;

    public UpdateDeniedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UpdateDeniedException(Throwable cause) {
        super(cause);
    }

    @Override
    protected int errorId() {
        return UPDATE_DENIED_EXCEPTION;
    }
}
