/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public static void main(String[] args) {
        double number = 1234567.89;

        String formatted = format(number);
        System.out.println("Formatted: " + formatted);

        try {
            double parsed = parse(formatted);
            System.out.println("Parsed: " + parsed);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String formatCurrency(double hocPhi) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(hocPhi);
    }

    public static String formatNumber(double number) {
        DecimalFormat df = new DecimalFormat("0.##");
        return df.format(number);
    }

    public static String replaceCommaWithDot(String input) {
        // Thay thế dấu phẩy bằng dấu chấm và trả về kết quả
        return input.replace(",", ".");
    }
}
