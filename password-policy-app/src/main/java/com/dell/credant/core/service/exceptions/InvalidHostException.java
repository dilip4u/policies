/**
 * Created by Dilip Kancharla on Jun 28, 2015
 */
package com.dell.credant.core.service.exceptions;

/**
 * @author Dilip Reddy Kancharla .
 * Raised when Hostname is invalid.
 */
public class InvalidHostException extends RuntimeException {

    /**
      * to assist in serialization.
     */
    private static final long serialVersionUID = 982832996103602892L;
    /**
     * paramterized constructor.
     * @param message - exception message
     * @param cause - Throwable cause.
     */
    public InvalidHostException(final String message,
            final Throwable cause) {
        super(message, cause);
    }
    /**
     * paramterized constructor.
     * @param message - message of exception.
     */
    public InvalidHostException(final String message) {
        super(message);
    }
    /**
     * Raised when Hostname is invalid.
     */
    public InvalidHostException() {
    /* this is intentionally empty, nothing needed here. This
        constructor is only here, so that it can be documented via javadoc.*/
    }
}
