package com.pokeshop.ecommerce.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class MathUtil {

    private BigDecimal value;

    public MathUtil(Object number) {
        this.value = this.getNumberBigDecimal(number, BigDecimal.ZERO);
    }

    public MathUtil(Object number, BigDecimal isNull) {
        this.value = this.getNumberBigDecimal(number, isNull);
    }

    public MathUtil add(Object number) {
        return this.add(number, (Integer) null);
    }

    public MathUtil add(Object number, Integer decimales) {
        BigDecimal value2;
        if (number instanceof MathUtil) {
            value2 = ((MathUtil) number).getBigDecimal();
        } else {
            value2 = (new MathUtil(number)).getBigDecimal();
        }

        this.value = this.value.add(value2);
        if (decimales != null) {
            this.value = new BigDecimal(StrUtil.getString(this.round(this.value.doubleValue(), decimales)));
        }

        return this;
    }

    public MathUtil min(Object number) {
        return this.min(number, (Integer) null);
    }

    public MathUtil min(Object number, Integer decimales) {
        BigDecimal value2;
        if (number instanceof MathUtil) {
            value2 = ((MathUtil) number).getBigDecimal();
        } else {
            value2 = (new MathUtil(number)).getBigDecimal();
        }

        this.value = this.value.subtract(value2);
        if (decimales != null) {
            this.value = new BigDecimal(StrUtil.getString(this.round(this.value.doubleValue(), decimales)));
        }

        return this;
    }

    public MathUtil mul(Object number) {
        return this.mul(number, (Integer) null);
    }

    public MathUtil mul(Object number, Integer decimales) {
        BigDecimal value2;
        if (number instanceof MathUtil) {
            value2 = ((MathUtil) number).getBigDecimal();
        } else {
            value2 = (new MathUtil(number)).getBigDecimal();
        }

        this.value = this.value.multiply(value2);
        if (decimales != null) {
            this.value = new BigDecimal(StrUtil.getString(this.round(this.value.doubleValue(), decimales)));
        }

        return this;
    }

    public MathUtil div(Object number) {
        return this.div(number, (Integer) null);
    }

    public MathUtil div(Object number, Integer decimales) {
        BigDecimal value2;
        if (number instanceof MathUtil) {
            value2 = ((MathUtil) number).getBigDecimal();
        } else {
            value2 = (new MathUtil(number)).getBigDecimal();
        }

        this.value = this.value.divide(value2, 20, RoundingMode.UP);
        if (decimales != null) {
            this.value = new BigDecimal(StrUtil.getString(this.round(this.value.doubleValue(), decimales)));
        }

        return this;
    }

    public BigDecimal getBigDecimal() {
        return this.value;
    }

    public Double getDouble() {
        return this.value.doubleValue();
    }

    public Integer getInteger() {
        return this.value.intValue();
    }

    private BigDecimal getNumberBigDecimal(Object data, BigDecimal isNull) {
        BigDecimal a;
        if (data != null) {
            if (data instanceof Integer) {
                a = new BigDecimal((Integer) data);
            } else if (data instanceof Double) {
                a = new BigDecimal((Double) data + "");
            } else if (data instanceof BigDecimal) {
                a = (BigDecimal) data;
            } else {
                a = BigDecimal.ZERO;
                String number = StrUtil.getString(data);
                NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());

                try {
                    a = new BigDecimal(StrUtil.getString(nf.parse(number).doubleValue()));
                } catch (Exception var7) {
                }
            }
        } else {
            a = isNull;
        }

        return a;
    }

    public MathUtil round(int decimales) {
        this.value = new BigDecimal(StrUtil.getString(this.round(this.value.doubleValue(), decimales)));
        return this;
    }

    private double round(double a, int decimal) {
        a = this.mul(a, Math.pow(10.0D, (double) decimal));
        a = (double) Math.round(a);
        a = this.div(a, Math.pow(10.0D, (double) decimal));
        return a;
    }

    private double mul(double a, double b) {
        BigDecimal bigA = new BigDecimal(a + "");
        BigDecimal bigB = new BigDecimal(b + "");
        bigA = bigA.multiply(bigB);
        return bigA.doubleValue();
    }

    private double div(double a, double b) {
        BigDecimal bigA = new BigDecimal(a + "");
        BigDecimal bigB = new BigDecimal(b + "");
        if (bigB.doubleValue() > 0.0D) {
            bigA = bigA.divide(bigB, 10, RoundingMode.UP);
            return bigA.doubleValue();
        } else {
            return 0.0D;
        }
    }

    static {
        Locale.setDefault(Locale.US);
    }

}