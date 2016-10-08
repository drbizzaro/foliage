package org.foilage.utils.checkers;

public class HttpURLChecker {

    public static String containsOnlyValidCharacters(String text) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("String ");
        stringBuilder.append(text);
        stringBuilder.append(" contains character outside ");
        stringBuilder.append(" a-zA-Z0-9_- ");

        return containsOnlyValidCharacters(text, stringBuilder.toString());
    }

    public static String containsOnlyValidCharacters(String text, String errorMessage) {

        if (text.matches("^[a-zA-Z0-9_-]+$")) {

            return text;

        } else {

            throw new IllegalArgumentException(errorMessage);
        }

    }
}
