package com.tannx.swt.controller;

import com.tannx.swt.DateTimeChecker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DateCheckerController {

    @GetMapping("/api/dayInMonth")
    public Map<String, Object> dayInMonth(
            @RequestParam(required = false, defaultValue = "") String month,
            @RequestParam(required = false, defaultValue = "") String year) {
        
        Map<String, Object> response = new HashMap<>();
        try {
            int m = Integer.parseInt(month);
            int y = Integer.parseInt(year);
            if (m < 1 || m > 12) {
                response.put("error", "Input data for Month is out of range!");
            } else if (y < 1000 || y > 3000) {
                response.put("error", "Input data for Year is out of range!");
            } else {
                int days = DateTimeChecker.getDaysInMonth(m, y);
                response.put("days", days);
            }
        } catch (NumberFormatException e) {
            response.put("error", "Input data is incorrect format!");
        }
        return response;
    }

    @GetMapping("/api/checkDate")
    public Map<String, String> checkDate(
            @RequestParam(required = false, defaultValue = "") String day,
            @RequestParam(required = false, defaultValue = "") String month,
            @RequestParam(required = false, defaultValue = "") String year) {

        String resultMessage = DateTimeChecker.checkDateString(day, month, year);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", resultMessage);
        
        return response;
    }
}
