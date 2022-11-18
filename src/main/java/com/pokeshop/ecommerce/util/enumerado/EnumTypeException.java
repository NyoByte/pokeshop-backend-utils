package com.pokeshop.ecommerce.util.enumerado;

public enum EnumTypeException {

    I("Informativo"),
    A("Advertencia"),
    E("Error");

    private final String value;

    EnumTypeException(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }

}