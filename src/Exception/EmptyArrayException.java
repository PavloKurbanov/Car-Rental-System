package Exception;

public class EmptyArrayException extends RuntimeException {
    public EmptyArrayException(String message, Throwable cause) {
        super(message,  cause);
    }
    public EmptyArrayException(String message) {
        super(message);
    }
}
