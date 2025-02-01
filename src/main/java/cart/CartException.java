package cart;

/**
 * Custom exception representing errors that can occur in the shopping cart.
 */
public class CartException extends Exception {

    public CartException(String message) {
        super(message);
    }

    public CartException(String message, Throwable cause) {
        super(message, cause);
    }
}