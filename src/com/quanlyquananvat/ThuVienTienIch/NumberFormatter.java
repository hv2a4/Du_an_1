package com.quanlyquananvat.ThuVienTienIch;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberFormatter {

    private static final String PATTERN = "###,###.00";
    private static final Locale LOCALE = new Locale("vi", "VN");

    private static DecimalFormat decimalFormat;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(LOCALE);
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator('.');

        decimalFormat = new DecimalFormat(PATTERN, symbols);
    }

    public static String format(double number) {
        return decimalFormat.format(number);
    }

    public static double parse(String text) throws ParseException {
        return decimalFormat.parse(text).doubleValue();
    }

    public static double parseFormattedNumber(String formattedNumber) throws ParseException {
        DecimalFormat df = new DecimalFormat("###,###.000");
        return df.parse(formattedNumber).doubleValue();
    }

    public static String formatCurrency(double hocPhi) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(hocPhi);
    }

    public static String formatNumber(double number) {
        DecimalFormat df = new DecimalFormat("###,##0.000");
        return df.format(number);
    }

    public static String replaceCommaWithDot(String input) {
        // Thay thế dấu phẩy bằng dấu chấm và trả về kết quả
        return input.replace(",", ".");
    }
}
