package org.foilage.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Now {

    public static TimeSourceType timeSourceType = TimeSourceType.SYSTEM;

    public static long currentTimeMillis() {

        switch (timeSourceType) {

            case SYSTEM:
                return System.currentTimeMillis();
        }

        throw new IllegalArgumentException("Time source error in method currentTimeMillis() for time source type "+timeSourceType.name());
    }

    public static LocalDate localDate() {

        switch (timeSourceType) {

            case SYSTEM:

                return LocalDate.now();
        }

        throw new IllegalArgumentException("Time source error in method date() for time source type "+timeSourceType.name());
    }

    public static LocalDateTime localDateTime() {

        switch (timeSourceType) {

            case SYSTEM:

                return LocalDateTime.now();
        }

        throw new IllegalArgumentException("Time source error in method date() for time source type "+timeSourceType.name());
    }

    public static Date date() {

        switch (timeSourceType) {

            case SYSTEM:
                return new Date();
        }

        throw new IllegalArgumentException("Time source error in method date() for time source type "+timeSourceType.name());
    }

    public static Calendar calendar() {

        switch (timeSourceType) {

            case SYSTEM:
                return Calendar.getInstance();
        }

        throw new IllegalArgumentException("Time source error in method calendar() for time source type "+timeSourceType.name());
    }

}
