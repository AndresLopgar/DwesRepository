package Laboral;
/**
 * Es la clase de datos no correctos
 */
public class DatosNoCorrectosException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
     * Constructor por defecto de la excepción.
     */
    public DatosNoCorrectosException() {
        super();
    }

    /**
     * Constructor que permite especificar un mensaje, una causa, la capacidad de suprimir la excepción y
     * la capacidad de hacer que la pila de llamadas sea escribible o no.
     *
     * @param message           Mensaje descriptivo de la excepción.
     * @param cause             Causa de la excepción.
     * @param enableSuppression Indica si la supresión de excepciones está habilitada o deshabilitada.
     * @param writableStackTrace Indica si la pila de llamadas es escribible o no.
     */
    public DatosNoCorrectosException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Constructor que permite especificar un mensaje y una causa.
     *
     * @param message Mensaje descriptivo de la excepción.
     * @param cause   Causa de la excepción.
     */
    public DatosNoCorrectosException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor que permite especificar un mensaje descriptivo.
     *
     * @param message Mensaje descriptivo de la excepción.
     */
    public DatosNoCorrectosException(String message) {
        super(message);
    }

    /**
     * Constructor que permite especificar una causa.
     *
     * @param cause Causa de la excepción.
     */
    public DatosNoCorrectosException(Throwable cause) {
        super(cause);
    }

}