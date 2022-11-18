package com.pokeshop.ecommerce.util;

import com.pokeshop.ecommerce.exception.ValidateException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

    public static final String EMAIL = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

    public ValidateUtil() {
    }

    public static void longitud(Object eval, int length, Operador oper, GenericError errorCode) {
        longitud((Object) eval, length, oper, errorCode, (String) null);
    }

    public static void longitud(Object eval, int length, Operador oper, GenericError errorCode, String mensaje) {
        longitud(StrUtil.getString(eval), length, oper, errorCode, mensaje);
    }

    public static void longitud(String eval, int length, Operador oper, GenericError errorCode) {
        longitud((String) eval, length, oper, errorCode, (String) null);
    }

    public static void longitud(String eval, int length, Operador oper, GenericError errorCode, String mensaje) {
        if (!StrUtil.getString(eval).isEmpty() && null != oper) {
            switch (oper) {
                case MAYOR:
                    evaluar(eval.length() > length, errorCode, mensaje);
                    break;
                case MAYORIGUAL:
                    evaluar(eval.length() >= length, errorCode, mensaje);
                    break;
                case IGUAL:
                    evaluar(eval.length() == length, errorCode, mensaje);
                    break;
                case MENORIGUAL:
                    evaluar(eval.length() <= length, errorCode, mensaje);
                    break;
                case MENOR:
                    evaluar(eval.length() < length, errorCode, mensaje);
            }
        }

    }

    public static void valorNumerico(Integer eval, double cantidad, Operador oper, GenericError errorCode) {
        valorNumerico((Integer) eval, cantidad, oper, errorCode, (String) null);
    }

    public static void valorNumerico(Integer eval, double cantidad, Operador oper, GenericError errorCode, String mensaje) {
        valorNumerico(eval.doubleValue(), cantidad, oper, errorCode, mensaje);
    }

    public static void valorNumerico(Double eval, double cantidad, Operador oper, GenericError errorCode) {
        valorNumerico((Double) eval, cantidad, oper, errorCode, (String) null);
    }

    public static void valorNumerico(Double eval, double cantidad, Operador oper, GenericError errorCode, String mensaje) {
        if (null != oper) {
            switch (oper) {
                case MAYOR:
                    evaluar(eval > cantidad, errorCode, mensaje);
                    break;
                case MAYORIGUAL:
                    evaluar(eval >= cantidad, errorCode, mensaje);
                    break;
                case IGUAL:
                    evaluar(eval == cantidad, errorCode, mensaje);
                    break;
                case MENORIGUAL:
                    evaluar(eval <= cantidad, errorCode, mensaje);
                    break;
                case MENOR:
                    evaluar(eval < cantidad, errorCode, mensaje);
            }
        }

    }

    public static void soloDigitos(String eval, GenericError errorCode) {
        soloDigitos(eval, errorCode, (String) null);
    }

    public static void soloDigitos(String eval, GenericError errorCode, String mensaje) {
        if (eval != null && !eval.isEmpty()) {
            String patron = "\\d*";
            if (!eval.matches(patron)) {
                if (mensaje != null) {
                    throw new ValidateException(errorCode, mensaje);
                }

                throw new ValidateException(errorCode);
            }
        }

    }

    public static void requerido(Object eval, GenericError errorCode) {
        requerido(eval, errorCode, (String) null);
    }

    public static void requerido(Object eval, GenericError errorCode, String mensaje) {
        if (eval == null) {
            if (mensaje != null) {
                throw new ValidateException(errorCode, mensaje);
            } else {
                throw new ValidateException(errorCode);
            }
        }
    }

    public static void expresion(String eval, String expresion, GenericError errorCode) {
        expresion(eval, expresion, errorCode, (String) null);
    }

    public static void expresion(String eval, String expresion, GenericError errorCode, String mensaje) {
        if (eval != null && !eval.isEmpty()) {
            Pattern p = Pattern.compile(expresion);
            Matcher m = p.matcher(eval);
            if (!m.find()) {
                if (mensaje != null) {
                    throw new ValidateException(errorCode, mensaje);
                }

                throw new ValidateException(errorCode);
            }
        }

    }

    public static void evaluar(boolean eval, GenericError errorCode) {
        evaluar(eval, errorCode, (String) null);
    }

    public static void evaluar(boolean eval, GenericError errorCode, String mensaje) {
        if (!eval) {
            if (mensaje != null) {
                throw new ValidateException(errorCode, mensaje);
            } else {
                throw new ValidateException(errorCode);
            }
        }
    }

    public static enum Operador {
        MAYOR(">"),
        MAYORIGUAL("<="),
        IGUAL("="),
        MENORIGUAL(">="),
        MENOR("<");

        private final String oper;

        private Operador(String oper) {
            this.oper = oper;
        }

        public String getOper() {
            return this.oper;
        }
    }

}