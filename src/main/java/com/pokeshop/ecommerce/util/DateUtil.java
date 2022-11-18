package com.pokeshop.ecommerce.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {

    public DateUtil() {
    }

    public static Date getLastDayMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, 1);
        calendar.set(5, 1);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static Date getFirstDayMonth(Date date) {
        String format = getDate(date, "01/MM/yyyy");
        return getDate(format, DateFormatType.FORM_05.getFormato());
    }

    public static Date getDate(String str, String format) {
        Date date = null;
        if (!StrUtil.getString(str).isEmpty()) {
            if (format != null) {
                return getDate(new SimpleDateFormat(format), str);
            } else {
                SimpleDateFormat sdf;
                switch (str.length()) {
                    case 2:
                        str = str + StrUtil.getMonth();
                        str = str + StrUtil.getYear();
                        return getDate(new SimpleDateFormat(DateFormatType.FORM_04.getFormato()), str);
                    case 3:
                    case 5:
                    case 7:
                    case 9:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    default:
                        return null;
                    case 4:
                        str = str + StrUtil.getYear();
                        return getDate(new SimpleDateFormat(DateFormatType.FORM_04.getFormato()), str);
                    case 6:
                        return getDate(new SimpleDateFormat(DateFormatType.FORM_01.getFormato()), str);
                    case 8:
                        sdf = format08(str);
                        return getDate(sdf, str);
                    case 10:
                        sdf = format10(str);
                        return getDate(sdf, str);
                    case 19:
                        sdf = new SimpleDateFormat(DateFormatType.FORM_09.getFormato());
                        return getDate(sdf, str);
                }
            }
        } else {
            return (Date) date;
        }
    }

    private static SimpleDateFormat format08(String str) {
        if (str.contains("/")) {
            return new SimpleDateFormat(DateFormatType.FORM_02.getFormato());
        } else {
            return str.contains("-") ? new SimpleDateFormat(DateFormatType.FORM_03.getFormato()) : new SimpleDateFormat(DateFormatType.FORM_04.getFormato());
        }
    }

    private static SimpleDateFormat format10(String str) {
        int index;
        if (str.contains("/")) {
            index = str.indexOf("/");
            if (index == 2) {
                return new SimpleDateFormat(DateFormatType.FORM_05.getFormato());
            }

            if (index == 4) {
                return new SimpleDateFormat(DateFormatType.FORM_06.getFormato());
            }
        } else if (str.contains("-")) {
            index = str.indexOf("-");
            if (index == 2) {
                return new SimpleDateFormat(DateFormatType.FORM_07.getFormato());
            }

            if (index == 4) {
                return new SimpleDateFormat(DateFormatType.FORM_08.getFormato());
            }
        }

        return null;
    }

    private static Date getDate(SimpleDateFormat sdf, String str) {
        try {
            if (sdf != null) {
                return sdf.parse(str);
            }
        } catch (ParseException var3) {
        }

        return null;
    }

    public static String getDate(Date date, String format) {
        String str = null;
        if (date != null && format != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                str = sdf.format(date);
            } catch (Exception var5) {
            }
        }

        return str;
    }

    public static String getDate(LocalDate date, String format) {
        String str = null;
        if (date != null && format != null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                str = date.format(formatter);
            } catch (Exception var5) {
            }
        }

        return str;
    }

    public static String getDate(LocalDateTime date, String format) {
        String str = null;
        if (date != null && format != null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                str = date.format(formatter);
            } catch (Exception var5) {
            }
        }

        return str;
    }

    public static Date getDate(String str) {
        return getDate((String) str, (String) null);
    }

    public static Date getDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getDate(Object obj) {
        return getDate((String) StrUtil.getString(obj), (String) null);
    }

    public static int getDiference(Date fecFin, Date fecIni, DateType tipo) {
        GregorianCalendar date1 = new GregorianCalendar();
        date1.setTime(fecIni);
        GregorianCalendar date2 = new GregorianCalendar();
        date2.setTime(fecFin);
        int rango = 0;
        if (null != tipo) {
            switch (tipo) {
                case HORAS:
                default:
                    break;
                case DIAS:
                    if (date1.get(1) == date2.get(1)) {
                        rango = date2.get(6) - date1.get(6);
                    } else {
                        int diasAnyo = date1.isLeapYear(date1.get(1)) ? 366 : 365;
                        int rangoAnyos = date2.get(1) - date1.get(1);
                        rango = rangoAnyos * diasAnyo + (date2.get(6) - date1.get(6));
                    }
                    break;
                case MESES:
                    rango = (date2.get(1) - date1.get(1)) * 12 + (date2.get(2) - date1.get(2));
                    break;
                case ANOS:
                    rango = date2.get(1) - date1.get(1);
            }
        }

        return rango;
    }

    public static Date addDate(Date fecha, DateType tipo, int valor) {
        String formato = getDate(fecha, "dd-MM-yyyy-HH-mm-ss");
        if (formato != null) {
            String[] sector = formato.split("-");
            if (null != tipo) {
                switch (tipo) {
                    case HORAS:
                        sector[3] = StrUtil.formatNumber((new MathUtil(sector[3])).add(valor).getDouble(), "00");
                        break;
                    case DIAS:
                        sector[0] = StrUtil.formatNumber((new MathUtil(sector[0])).add(valor).getDouble(), "00");
                        break;
                    case MESES:
                        sector[1] = StrUtil.formatNumber((new MathUtil(sector[1])).add(valor).getDouble(), "00");
                        break;
                    case ANOS:
                        sector[2] = StrUtil.formatNumber((new MathUtil(sector[2])).add(valor).getDouble(), "0000");
                        break;
                    case MINUTOS:
                        sector[4] = StrUtil.formatNumber((new MathUtil(sector[4])).add(valor).getDouble(), "00");
                }
            }

            formato = sector[0] + "-" + sector[1] + "-" + sector[2] + "-" + sector[3] + "-" + sector[4] + "-" + sector[5];
            return getDate(formato, "dd-MM-yyyy-HH-mm-ss");
        } else {
            return null;
        }
    }

    public static int getDayOfMonth(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth();
    }

    public static String getNameOfMonth(LocalDate date) {
        return date.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
    }

    public static String getNameOfMonth(Date date) {
        return getNameOfMonth(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public static enum DateFormatType {
        FORM_01("ddMMyy"),
        FORM_02("dd/MM/yy"),
        FORM_03("dd-MM-yy"),
        FORM_04("ddMMyyyy"),
        FORM_05("dd/MM/yyyy"),
        FORM_06("yyyy/MM/dd"),
        FORM_07("dd-MM-yyyy"),
        FORM_08("yyyy-MM-dd"),
        FORM_09("yyyy-MM-dd HH:mm:ss");

        private final String formato;

        private DateFormatType(String formato) {
            this.formato = formato;
        }

        public String getFormato() {
            return this.formato;
        }
    }

    public static enum DateType {
        MINUTOS(2),
        HORAS(3),
        DIAS(4),
        MESES(5),
        ANOS(6);

        private final int type;

        private DateType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }
    }

}