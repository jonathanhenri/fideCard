
package com.fidecard.application.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateUtils {
    private static final long SECOND = 1000;
    private static final long MINUTE = 60 * SECOND;
    private static final long HOUR = 60 * MINUTE;
    private static final long DAY = 24 * HOUR;
    private static final long YEAR = 365 * DAY;

    public Date getNow() {
        return Date.from(Instant.now());
    }

    public Date addSecondsToDate(Date date, long secondsToAdd) {
        return new Date(date.getTime() + (secondsToAdd * SECOND));
    }

    public Date removeSecondsToDate(Date date, long secondsToAdd) {
        return new Date(date.getTime() - (secondsToAdd * SECOND));
    }

    public Date addYearsToDate(Date date, long yearsToAdd) {
        return new Date(date.getTime() + (yearsToAdd * YEAR));
    }
    
    public static Date convertStringToData(String data) {
        Date dataEntrada;
        if (StringUtils.isEmpty(data)) {
            dataEntrada = new Date();
        } else {
            dataEntrada = Utils.asDate(data);
        }
        return dataEntrada;
    }
    
    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
