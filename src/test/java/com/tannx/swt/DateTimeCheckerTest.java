package com.tannx.swt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateTimeCheckerTest {

    // ==========================================================
    // 1. Test cases for "getDaysInMonth" function
    // ==========================================================

    @Test
    @DisplayName("Test getDaysInMonth: Month with 31 days (Jan 2024)")
    void testGetDaysInMonth_31Days() {
        assertEquals(30, DateTimeChecker.getDaysInMonth(1, 2024));
    }

    @Test
    @DisplayName("Test getDaysInMonth: Month with 30 days (Apr 2024)")
    void testGetDaysInMonth_30Days() {
        assertEquals(30, DateTimeChecker.getDaysInMonth(4, 2024));
    }

    @Test
    @DisplayName("Test getDaysInMonth: February in Leap Year")
    void testGetDaysInMonth_LeapYear_Feb() {
        assertEquals(29, DateTimeChecker.getDaysInMonth(2, 2024));
    }

    @Test
    @DisplayName("Test getDaysInMonth: February in Non-Leap Year")
    void testGetDaysInMonth_NonLeapYear_Feb() {
        assertEquals(28, DateTimeChecker.getDaysInMonth(2, 2023));
    }

    @Test
    @DisplayName("Test getDaysInMonth: Invalid Month")
    void testGetDaysInMonth_InvalidMonth() {
        assertEquals(0, DateTimeChecker.getDaysInMonth(13, 2024));
    }

    // ==========================================================
    // 2. Test cases for "isValidDate" based on Decision Table
    //    Focus: Boundary values for Day in Month (Leap Year logic)
    // ==========================================================

    @Test
    @DisplayName("UTCID01: 29/2/2000 - Leap year (divisible by 400)")
    void testIsValidDate_UTCID01() {
        assertTrue(DateTimeChecker.isValidDate(29, 2, 2000));
    }

    @Test
    @DisplayName("UTCID02: 29/2/2009 - Non-leap year")
    void testIsValidDate_UTCID02() {
        assertFalse(DateTimeChecker.isValidDate(29, 2, 2009));
    }

    @Test
    @DisplayName("UTCID03: 29/2/2020 - Leap year (divisible by 4)")
    void testIsValidDate_UTCID03() {
        assertTrue(DateTimeChecker.isValidDate(29, 2, 2020));
    }

    @Test
    @DisplayName("UTCID04: 30/2/2000 - Invalid day for Feb (Leap year)")
    void testIsValidDate_UTCID04() {
        assertFalse(DateTimeChecker.isValidDate(30, 2, 2000));
    }

    @Test
    @DisplayName("UTCID05: 30/2/2009 - Invalid day for Feb (Non-leap year)")
    void testIsValidDate_UTCID05() {
        assertFalse(DateTimeChecker.isValidDate(30, 2, 2009));
    }

    @Test
    @DisplayName("UTCID06: 30/2/2020 - Invalid day for Feb (Leap year)")
    void testIsValidDate_UTCID06() {
        assertFalse(DateTimeChecker.isValidDate(30, 2, 2020));
    }

    @Test
    @DisplayName("UTCID07: 31/3/2000 - Valid day for March")
    void testIsValidDate_UTCID07() {
        assertTrue(DateTimeChecker.isValidDate(31, 3, 2000));
    }

    @Test
    @DisplayName("UTCID08: 31/3/2009 - Valid day for March")
    void testIsValidDate_UTCID08() {
        assertTrue(DateTimeChecker.isValidDate(31, 3, 2009));
    }

    @Test
    @DisplayName("UTCID09: 31/3/2020 - Valid day for March")
    void testIsValidDate_UTCID09() {
        assertTrue(DateTimeChecker.isValidDate(31, 3, 2020));
    }

    @Test
    @DisplayName("UTCID10: 31/4/2000 - Invalid day for April")
    void testIsValidDate_UTCID10() {
        assertFalse(DateTimeChecker.isValidDate(31, 4, 2000));
    }

    @Test
    @DisplayName("UTCID11: 31/4/2009 - Invalid day for April")
    void testIsValidDate_UTCID11() {
        assertFalse(DateTimeChecker.isValidDate(31, 4, 2009));
    }

    @Test
    @DisplayName("UTCID12: 31/4/2020 - Invalid day for April")
    void testIsValidDate_UTCID12() {
        assertFalse(DateTimeChecker.isValidDate(31, 4, 2020));
    }

    @Test
    @DisplayName("UTCID13: 31/2/2000 - Invalid day for Feb")
    void testIsValidDate_UTCID13() {
        assertFalse(DateTimeChecker.isValidDate(31, 2, 2000));
    }

    @Test
    @DisplayName("UTCID14: 31/2/2009 - Invalid day for Feb")
    void testIsValidDate_UTCID14() {
        assertFalse(DateTimeChecker.isValidDate(31, 2, 2009));
    }

    @Test
    @DisplayName("UTCID15: 31/2/2020 - Invalid day for Feb")
    void testIsValidDate_UTCID15() {
        assertFalse(DateTimeChecker.isValidDate(31, 2, 2020));
    }

    // ==========================================================
    // 3. Test cases for "checkDateString" function (Full Coverage)
    //    Focus: Format validation and Out of Range errors
    // ==========================================================

    @Test
    @DisplayName("Test checkDateString: Incorrect format for Day")
    void testCheckDateString_IncorrectFormatDay() {
        String result = DateTimeChecker.checkDateString("abc", "2", "2024");
        assertEquals("Input data for Day is incorrect format!", result);
    }

    @Test
    @DisplayName("Test checkDateString: Incorrect format for Month")
    void testCheckDateString_IncorrectFormatMonth() {
        String result = DateTimeChecker.checkDateString("29", "xyz", "2024");
        assertEquals("Input data for Month is incorrect format!", result);
    }

    @Test
    @DisplayName("Test checkDateString: Incorrect format for Year")
    void testCheckDateString_IncorrectFormatYear() {
        String result = DateTimeChecker.checkDateString("29", "2", "");
        assertEquals("Input data for Year is incorrect format!", result);
    }

    @Test
    @DisplayName("Test checkDateString: Day out of range (lower bound)")
    void testCheckDateString_DayOutOfRangeLower() {
        String result = DateTimeChecker.checkDateString("0", "2", "2024");
        assertEquals("Input data for Day is out of range!", result);
    }

    @Test
    @DisplayName("Test checkDateString: Day out of range (upper bound)")
    void testCheckDateString_DayOutOfRangeUpper() {
        String result = DateTimeChecker.checkDateString("32", "1", "2024");
        assertEquals("Input data for Day is out of range!", result);
    }

    @Test
    @DisplayName("Test checkDateString: Month out of range (lower bound)")
    void testCheckDateString_MonthOutOfRangeLower() {
        String result = DateTimeChecker.checkDateString("15", "0", "2024");
        assertEquals("Input data for Month is out of range!", result);
    }

    @Test
    @DisplayName("Test checkDateString: Month out of range (upper bound)")
    void testCheckDateString_MonthOutOfRangeUpper() {
        String result = DateTimeChecker.checkDateString("15", "13", "2024");
        assertEquals("Input data for Month is out of range!", result);
    }

    @Test
    @DisplayName("Test checkDateString: Year out of range (lower bound)")
    void testCheckDateString_YearOutOfRangeLower() {
        String result = DateTimeChecker.checkDateString("15", "5", "999");
        assertEquals("Input data for Year is out of range!", result);
    }

    @Test
    @DisplayName("Test checkDateString: Year out of range (upper bound)")
    void testCheckDateString_YearOutOfRangeUpper() {
        String result = DateTimeChecker.checkDateString("15", "5", "3001");
        assertEquals("Input data for Year is out of range!", result);
    }

    @Test
    @DisplayName("Test checkDateString: Valid date time")
    void testCheckDateString_ValidDate() {
        String result = DateTimeChecker.checkDateString("29", "2", "2024");
        assertEquals("29/2/2024 is correct date time!", result);
    }

    @Test
    @DisplayName("Test checkDateString: Invalid date time")
    void testCheckDateString_InvalidDate() {
        String result = DateTimeChecker.checkDateString("29", "2", "2023");
        assertEquals("29/2/2023 is invalid date time!", result);
    }

    // ==========================================================
    // 4. Test cases for "checkDateString" based on Decision Table
    //    Focus: Boundary values mapped to String output
    // ==========================================================

    @Test
    @DisplayName("CheckDate UTCID01: 29/2/2000 - Leap year")
    void testCheckDateString_UTCID01() {
        assertEquals("29/2/2000 is correct date time!", DateTimeChecker.checkDateString("29", "2", "2000"));
    }

    @Test
    @DisplayName("CheckDate UTCID02: 29/2/2009 - Non-leap year")
    void testCheckDateString_UTCID02() {
        assertEquals("29/2/2009 is invalid date time!", DateTimeChecker.checkDateString("29", "2", "2009"));
    }

    @Test
    @DisplayName("CheckDate UTCID03: 29/2/2020 - Leap year")
    void testCheckDateString_UTCID03() {
        assertEquals("29/2/2020 is correct date time!", DateTimeChecker.checkDateString("29", "2", "2020"));
    }

    @Test
    @DisplayName("CheckDate UTCID04: 30/2/2000 - Invalid")
    void testCheckDateString_UTCID04() {
        assertEquals("30/2/2000 is invalid date time!", DateTimeChecker.checkDateString("30", "2", "2000"));
    }

    @Test
    @DisplayName("CheckDate UTCID05: 30/2/2009 - Invalid")
    void testCheckDateString_UTCID05() {
        assertEquals("30/2/2009 is invalid date time!", DateTimeChecker.checkDateString("30", "2", "2009"));
    }

    @Test
    @DisplayName("CheckDate UTCID06: 30/2/2020 - Invalid")
    void testCheckDateString_UTCID06() {
        assertEquals("30/2/2020 is invalid date time!", DateTimeChecker.checkDateString("30", "2", "2020"));
    }

    @Test
    @DisplayName("CheckDate UTCID07: 31/3/2000 - Valid")
    void testCheckDateString_UTCID07() {
        assertEquals("31/3/2000 is correct date time!", DateTimeChecker.checkDateString("31", "3", "2000"));
    }

    @Test
    @DisplayName("CheckDate UTCID08: 31/3/2009 - Valid")
    void testCheckDateString_UTCID08() {
        assertEquals("31/3/2009 is correct date time!", DateTimeChecker.checkDateString("31", "3", "2009"));
    }

    @Test
    @DisplayName("CheckDate UTCID09: 31/3/2020 - Valid")
    void testCheckDateString_UTCID09() {
        assertEquals("31/3/2020 is correct date time!", DateTimeChecker.checkDateString("31", "3", "2020"));
    }

    @Test
    @DisplayName("CheckDate UTCID10: 31/4/2000 - Invalid")
    void testCheckDateString_UTCID10() {
        assertEquals("31/4/2000 is invalid date time!", DateTimeChecker.checkDateString("31", "4", "2000"));
    }

    @Test
    @DisplayName("CheckDate UTCID11: 31/4/2009 - Invalid")
    void testCheckDateString_UTCID11() {
        assertEquals("31/4/2009 is invalid date time!", DateTimeChecker.checkDateString("31", "4", "2009"));
    }

    @Test
    @DisplayName("CheckDate UTCID12: 31/4/2020 - Invalid")
    void testCheckDateString_UTCID12() {
        assertEquals("31/4/2020 is invalid date time!", DateTimeChecker.checkDateString("31", "4", "2020"));
    }

    @Test
    @DisplayName("CheckDate UTCID13: 31/2/2000 - Invalid")
    void testCheckDateString_UTCID13() {
        assertEquals("31/2/2000 is invalid date time!", DateTimeChecker.checkDateString("31", "2", "2000"));
    }

    @Test
    @DisplayName("CheckDate UTCID14: 31/2/2009 - Invalid")
    void testCheckDateString_UTCID14() {
        assertEquals("31/2/2009 is invalid date time!", DateTimeChecker.checkDateString("31", "2", "2009"));
    }

    @Test
    @DisplayName("CheckDate UTCID15: 31/2/2020 - Invalid")
    void testCheckDateString_UTCID15() {
        assertEquals("31/2/2020 is invalid date time!", DateTimeChecker.checkDateString("31", "2", "2020"));
    }
}
