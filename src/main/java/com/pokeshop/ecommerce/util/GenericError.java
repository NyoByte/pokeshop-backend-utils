package com.pokeshop.ecommerce.util;

import com.pokeshop.ecommerce.util.enumerado.EnumTypeException;

public interface GenericError {

    String getCodigo();

    String getMensaje();

    EnumTypeException getType();

}