package org.foilage.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Now {

    public static TimeSourceType timeSourceType = TimeSourceType.SYSTEM;

    public static long currentTimeMillis() {

        switch (timeSourceType) {

            case SYSTEM:
                return System.currentTimeMillis();
            case EMULATED_STATIC:
                return timeSourceType.getEmulatedTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }

        throw new IllegalArgumentException("Time source error in method currentTimeMillis() for time source type "+timeSourceType.name());
    }

    public static LocalDate localDate() {

        switch (timeSourceType) {

            case SYSTEM:
                return LocalDate.now();
            case EMULATED_STATIC:
                return timeSourceType.getEmulatedTime().toLocalDate();
        }

        throw new IllegalArgumentException("Time source error in method date() for time source type "+timeSourceType.name());
    }

    public static LocalDateTime localDateTime() {

        switch (timeSourceType) {

            case SYSTEM:
                return LocalDateTime.now();
            case EMULATED_STATIC:
                return timeSourceType.getEmulatedTime();
        }

        throw new IllegalArgumentException("Time source error in method date() for time source type "+timeSourceType.name());
    }

    public static Date date() {

        switch (timeSourceType) {

            case SYSTEM:
                return new Date();
            case EMULATED_STATIC:
                return LocalDateTimeUtil.getAsDate(timeSourceType.getEmulatedTime());
        }

        throw new IllegalArgumentException("Time source error in method date() for time source type "+timeSourceType.name());
    }

    public static Calendar calendar() {

        switch (timeSourceType) {

            case SYSTEM:
                return Calendar.getInstance();
            case EMULATED_STATIC:
                return new GregorianCalendar(timeSourceType.getEmulatedTime().getYear(), timeSourceType.getEmulatedTime().getMonthValue()-1, timeSourceType.getEmulatedTime().getDayOfMonth(), timeSourceType.getEmulatedTime().getHour(), timeSourceType.getEmulatedTime().getMinute(), timeSourceType.getEmulatedTime().getSecond());
        }

        throw new IllegalArgumentException("Time source error in method calendar() for time source type "+timeSourceType.name());
    }

}
