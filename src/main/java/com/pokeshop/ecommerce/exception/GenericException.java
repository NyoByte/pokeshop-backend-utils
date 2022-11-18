package com.pokeshop.ecommerce.exception;

import com.pokeshop.ecommerce.util.enumerado.EnumTypeException;
import com.pokeshop.ecommerce.util.GenericError;

public class GenericException extends RuntimeException {

    private static final long serialVersionUID = -8315299222036414288L;
    private final String codigo;
    private final EnumTypeException type;

    public GenericException(GenericError error) {
        super(error.getMensaje());
        this.codigo = error.getCodigo();
        this.type = error.getType();
    }

    public GenericException(GenericError error, String message, Throwable cause) {
        super(message, cause);
        this.codigo = error.getCodigo();
        this.type = error.getType();
    }

    public GenericException(GenericError error, String message) {
        super(message);
        this.codigo = error.getCodigo();
        this.type = error.getType();
    }

    public String getCodigo() {
        return this.codigo;
    }

    public EnumTypeException getType() {
        return this.type;
    }

}