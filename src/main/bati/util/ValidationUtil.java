package main.bati.util;

import java.time.LocalDate;

public class ValidationUtil {

    // Validate if string is not empty
    public static boolean isStringValid(String str) {
        return str != null && !str.trim().isEmpty();
    }

    // Validate if a number is greater than zero
    public static boolean isPositiveNumber(double num) {
        return num > 0;
    }

    // Validate if a date is not in the past
    public static boolean isDateValid(LocalDate date) {
        return date != null && !date.isBefore(LocalDate.now());
    }

    // Generic null check
    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    // Validate phone number format (example: checks length)
    public static boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }
}
