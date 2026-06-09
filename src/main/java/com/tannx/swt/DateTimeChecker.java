package com.tannx.swt;

public class DateTimeChecker {

    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }
        if (year % 100 == 0) {
            return false;
        }
        return year % 4 == 0;
    }

    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 0; // Invalid month
        }
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year < 1000 || year > 3000) {
            return false; // Based on spec year in 1000-3000
        }
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > getDaysInMonth(month, year)) {
            return false;
        }
        return true;
    }

    // A method to simulate the whole logic to return message strings as the web/UI will need
    public static String checkDateString(String dayStr, String monthStr, String yearStr) {
        int day, month, year;
        
        try {
            day = Integer.parseInt(dayStr);
        } catch (NumberFormatException e) {
            return "Input data for Day is incorrect format!";
        }

        try {
            month = Integer.parseInt(monthStr);
        } catch (NumberFormatException e) {
            return "Input data for Month is incorrect format!";
        }

        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            return "Input data for Year is incorrect format!";
        }

        if (day < 1 || day > 31) {
            return "Input data for Day is out of range!";
        }
        if (month < 1 || month > 12) {
            return "Input data for Month is out of range!";
        }
        if (year < 1000 || year > 3000) {
            return "Input data for Year is out of range!";
        }

        if (isValidDate(day, month, year)) {
            return String.format("%d/%d/%d is correct date time!", day, month, year);
        } else {
            return String.format("%d/%d/%d is invalid date time!", day, month, year);
        }
    }
}
