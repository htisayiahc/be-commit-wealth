package be.commit_wealth.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateConverter {

    /**
     * แปลง String เป็น LocalDateTime โดยกำหนด Format ได้เอง
     * @param dateStr ข้อความวันที่ เช่น "2026-06-02 13:12:00"
     * @param pattern รูปแบบของวันที่ เช่น "yyyy-MM-dd HH:mm:ss"
     * @return LocalDateTime
     */
    public static LocalDateTime convertStringToDateTime(String dateStr, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            log.error("String datetime format error: {}", e.getMessage());
            throw new RuntimeException("String datetime format error: " + e.getMessage());
        }
    }
}