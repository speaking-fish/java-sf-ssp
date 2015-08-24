/**
 * 
 */
package com.speakingfish.protocol.ssp;

/**
 * @author borka
 *
 */
public class InvalidDataException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -2703913385806322448L;

    /**
     * 
     */
    public InvalidDataException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public InvalidDataException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public InvalidDataException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public InvalidDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
