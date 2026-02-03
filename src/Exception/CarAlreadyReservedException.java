package Exception;

public class CarAlreadyReservedException extends RuntimeException {
    public CarAlreadyReservedException(String message) {
        super(message);
    }
    public CarAlreadyReservedException(String message, Throwable cause) {
        super(message, cause);
    }
}
