package org.foilage.utils.checkers;

import org.foilage.utils.log.Log;

public class MaxLengthChecker {

    public static String maxLength(String text, int maxLength) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("String length ");
        stringBuilder.append(text.length());
        stringBuilder.append(" longer then max allowed ");
        stringBuilder.append(maxLength);
        stringBuilder.append(" for string: ");
        stringBuilder.append(text);

        return maxLength(text, maxLength, stringBuilder.toString());
    }

    public static String maxLength(String text, int maxLength, String errorMessage) {

        if(text.length()>maxLength) {

            Log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        return text;
    }

}
