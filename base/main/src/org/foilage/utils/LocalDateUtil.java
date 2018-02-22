package org.foilage.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public enum LocalDateUtil {

    I;

    public static void main(String[] args) {

        System.out.println(LocalDateUtil.getFromGovernmentId("1111133519").format(DateTimeFormatter.BASIC_ISO_DATE));
    }
    public static LocalDate getFromGovernmentId(String governmentId) {

        if(governmentId==null || governmentId.length()<1) {

            return null;
        }

        int year = Integer.parseInt(governmentId.substring(0, 2));
        int month = Integer.parseInt(governmentId.substring(2, 4));
        int day = Integer.parseInt(governmentId.substring(4, 6));

        if( (2000+year) <= LocalDate.now().getYear() ) {

            year += 2000;

        } else {

            year += 1900;
        }

        return LocalDate.of(year, month, day);
    }
}
