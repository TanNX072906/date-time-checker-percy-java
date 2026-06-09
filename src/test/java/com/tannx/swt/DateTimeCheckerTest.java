package com.tannx.swt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateTimeCheckerTest {

    // ==========================================================
    // 3. Test code for each test case of “DayInMonth” function.
    // ==========================================================

    @Test
    @DisplayName("TC_DayInMonth_01: Normal month with 31 days (Jan 2024)")
    void testDayInMonth_31Days() {
        int result = DateTimeChecker.getDaysInMonth(1, 2024);
        assertEquals(31, result);
    }

    @Test
    @DisplayName("TC_DayInMonth_02: Normal month with 30 days (Apr 2024)")
    void testDayInMonth_30Days() {
        int result = DateTimeChecker.getDaysInMonth(4, 2024);
        assertEquals(30, result);
    }

    @Test
    @DisplayName("TC_DayInMonth_03: February in Leap Year (Feb 2024)")
    void testDayInMonth_LeapYear_Feb() {
        int result = DateTimeChecker.getDaysInMonth(2, 2024);
        assertEquals(29, result);
    }

    @Test
    @DisplayName("TC_DayInMonth_04: February in Non-Leap Year (Feb 2023)")
    void testDayInMonth_NonLeapYear_Feb() {
        int result = DateTimeChecker.getDaysInMonth(2, 2023);
        assertEquals(28, result);
    }

    @Test
    @DisplayName("TC_DayInMonth_05: Invalid Month Input (13)")
    void testDayInMonth_InvalidMonth() {
        int result = DateTimeChecker.getDaysInMonth(13, 2024);
        assertEquals(0, result); // Expect 0 for invalid month
    }


    // ==========================================================
    // 4. Test code for each test case of “CheckDate” function.
    // ==========================================================

    @Test
    @DisplayName("TC_CheckDate_01: Valid leap year date (29/02/2024)")
    void testCheckDate_ValidLeapYearDate() {
        boolean result = DateTimeChecker.isValidDate(29, 2, 2024);
        assertTrue(result);
    }

    @Test
    @DisplayName("TC_CheckDate_02: Invalid leap year date (29/02/2023)")
    void testCheckDate_InvalidLeapYearDate() {
        boolean result = DateTimeChecker.isValidDate(29, 2, 2023);
        assertFalse(result);
    }

    @Test
    @DisplayName("TC_CheckDate_03: Invalid day for normal month (31/04/2024)")
    void testCheckDate_InvalidDayForMonth() {
        boolean result = DateTimeChecker.isValidDate(31, 4, 2024);
        assertFalse(result);
    }

    @Test
    @DisplayName("TC_CheckDate_04: Valid normal date (31/01/2024)")
    void testCheckDate_ValidNormalDate() {
        boolean result = DateTimeChecker.isValidDate(31, 1, 2024);
        assertTrue(result);
    }

    @Test
    @DisplayName("TC_CheckDate_05: Day out of lower bound (0/1/2024)")
    void testCheckDate_DayLowerBound() {
        boolean result = DateTimeChecker.isValidDate(0, 1, 2024);
        assertFalse(result);
    }

    @Test
    @DisplayName("TC_CheckDate_06: Day out of upper bound (32/1/2024)")
    void testCheckDate_DayUpperBound() {
        boolean result = DateTimeChecker.isValidDate(32, 1, 2024);
        assertFalse(result);
    }

    @Test
    @DisplayName("TC_CheckDate_07: Month out of lower bound (1/0/2024)")
    void testCheckDate_MonthLowerBound() {
        boolean result = DateTimeChecker.isValidDate(1, 0, 2024);
        assertFalse(result);
    }

    @Test
    @DisplayName("TC_CheckDate_08: Month out of upper bound (1/13/2024)")
    void testCheckDate_MonthUpperBound() {
        boolean result = DateTimeChecker.isValidDate(1, 13, 2024);
        assertFalse(result);
    }

    @Test
    @DisplayName("TC_CheckDate_09: Year out of lower bound (1/1/999)")
    void testCheckDate_YearLowerBound() {
        boolean result = DateTimeChecker.isValidDate(1, 1, 999);
        assertFalse(result);
    }

    @Test
    @DisplayName("TC_CheckDate_10: Year out of upper bound (1/1/3001)")
    void testCheckDate_YearUpperBound() {
        boolean result = DateTimeChecker.isValidDate(1, 1, 3001);
        assertFalse(result);
    }
}
