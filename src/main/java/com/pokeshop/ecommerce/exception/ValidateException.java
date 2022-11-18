package com.pokeshop.ecommerce.exception;

import com.pokeshop.ecommerce.util.GenericError;

public class ValidateException extends GenericException {

    private static final long serialVersionUID = -13421537771882130L;

    public ValidateException(GenericError error, String message) {
        super(error, message);
    }

    public ValidateException(GenericError error) {
        super(error);
    }

}