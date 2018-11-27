package application;

/**
 * Excepciones propias de entorno de calculadora
 * @author Javier
 */
public class CalculadoraException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new exception.
     */
    public CalculadoraException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public CalculadoraException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause (which is saved for later retrieval by the Exception.getCause() method).
     */
    public CalculadoraException(String message, Throwable cause) {
        super(message, cause);
    }
}