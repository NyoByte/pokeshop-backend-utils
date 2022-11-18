package com.pokeshop.ecommerce.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class StrUtil {

    public StrUtil() {
    }

    public static String getString(Object data) {
        if (data != null) {
            String str;
            if (data instanceof byte[]) {
                str = (new String((byte[]) data)).trim();
            } else {
                str = String.valueOf(data).trim();
            }

            return str;
        } else {
            return "";
        }
    }

    public static String getString(Object data, int length) {
        if (data != null) {
            String str = String.valueOf(data);
            if (str.length() > length) {
                str = str.substring(0, length);
            }

            return str.trim();
        } else {
            return "";
        }
    }

    public static String getString(Object data, int lenIni, int lenFin) {
        if (data != null && lenIni >= 0 && lenFin >= 0 && lenFin >= lenIni) {
            String str = String.valueOf(data);
            int len = str.length();
            if (len < lenIni) {
                str = "";
            } else if (len < lenFin) {
                str = str.substring(lenIni, len);
            } else {
                str = str.substring(lenIni, lenFin);
            }

            return str.trim();
        } else {
            return "";
        }
    }

    public static String subString(Object data, int length) {
        if (data != null) {
            String str = String.valueOf(data);
            if (length < str.length()) {
                str = str.substring(length);
            } else {
                str = "";
            }

            return str;
        } else {
            return "";
        }
    }

    public static String cutString(Object data, int length) {
        if (data != null) {
            String str = String.valueOf(data);
            if (str.length() > length) {
                str = str.substring(0, length);
            }

            return str;
        } else {
            return "";
        }
    }

    public static String formatNumber(String number, String pattern) {
        return formatNumber((new MathUtil(number)).getDouble(), pattern);
    }

    public static String formatNumber(double number, String pattern) {
        String format = "";

        try {
            DecimalFormat df = new DecimalFormat(pattern);
            format = df.format(number);
        } catch (Exception var5) {
            System.err.print("");
        }

        return format;
    }

    public static String formatNumber(Object number, int decimal) {
        String format = "";
        String pattern = numberPattern(decimal);
        MathUtil num = (new MathUtil(number)).round(decimal);
        try {
            DecimalFormat df = new DecimalFormat(pattern);
            format = df.format(num);
        } catch (Exception var5) {
            System.err.print("");
        }
        return format;
    }

    public static String decimalPattern(int decimal) {
        return "0." + rPad("", '0', decimal);
    }

    public static String numberPattern(int decimal) {
        StringBuilder sb = new StringBuilder("");
        sb.append("###,###,###,###,##0.");
        sb.append(rPad("", '0', decimal));
        return sb.toString();
    }

    public static String lPad(String text, char c, int length) {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < length - text.length(); ++i) {
            sb.append(c);
        }

        sb.append(text);
        return sb.toString();
    }

    public static String rPad(String text, char c, int length) {
        StringBuilder sb = new StringBuilder(text);

        for (int i = 0; i < length - text.length(); ++i) {
            sb.append(c);
        }

        return sb.toString();
    }

    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        return s;
    }

    public static String toFecha(String str) {
        return getString(str).isEmpty() ? "" : toFecha(str, "dd/MM/yyyy");
    }

    public static String toFecha(String str, String format) {
        if (format != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date fecha = DateUtil.getDate(str);
            return fecha == null ? "" : sdf.format(fecha);
        } else {
            return "";
        }
    }

    public static String getDate(Date date) {
        return getDate(date, "dd/MM/yyyy");
    }

    public static String getDate(LocalDate date) {
        return getDate(date, "dd/MM/yyyy");
    }

    public static String getDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return getDate(date, sdf);
    }

    public static String getDate(LocalDate date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return getDate(date, sdf);
    }

    public static String getDate(Date date, SimpleDateFormat format) {
        return date != null && format != null ? format.format(date) : "";
    }

    public static String getDate(LocalDate date, SimpleDateFormat format) {
        Instant instant = date.atTime(LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant();
        Date fecha = Date.from(instant);
        return fecha != null && format != null ? format.format(fecha) : "";
    }

    public static String getYear() {
        return getYear((Date) null);
    }

    public static String getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }

        int year = calendar.get(1);
        return year + "";
    }

    public static String getMonth() {
        return getMonth((Date) null);
    }

    public static String getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }

        return formatNumber((double) calendar.get(2) + 1.0D, "00");
    }

    public static String lPad(String word, int repeat, char val) {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < repeat - word.length(); ++i) {
            sb = sb.append(val);
        }

        return sb.append(word).toString();
    }

    public static boolean contains(String word, String[] value) {
        String[] var2 = value;
        int var3 = value.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String val = var2[var4];
            if (word.contains(val)) {
                return true;
            }
        }

        return false;
    }

    public static String space(int value) {
        String space = "";

        for (int i = 0; i < value; ++i) {
            space = space.concat(" ");
        }

        return space;
    }

    public static String toJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static String capitalizeFirstLetter(String text) {
        return StringUtils.capitalize(text);
    }

}