/**
 * Created by Dilip Kancharla on Jul 6, 2015
 */
package com.dell.credant.ws.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Created by Dilip Kancharla.
 * Raised in case of data validation failures.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    /**
     * ServialVersion Id to facilitate serialization.
     */
     private static final long serialVersionUID = 6903898942264867114L;

     /**
     * Raised in case of data validation failures.
     */
    public BadRequestException() {
    /* this is intentionally empty, nothing needed here. This
    constructor is only here, so that it can be documented via javadoc.*/
    }

    /**
     * Raised in case of data validation failures.
     * @param cause Throwable object
     */
    public BadRequestException(final Throwable cause) {
        super(cause);
    }
}
