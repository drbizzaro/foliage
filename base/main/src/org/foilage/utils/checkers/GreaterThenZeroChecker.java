package org.foilage.utils.checkers;

import org.pmw.tinylog.Logger;

public class GreaterThenZeroChecker {

    public static int greaterThenZero(int variable) {

        return greaterThenZero(variable, "Int can't be zero or negative.");
    }

    public static int greaterThenZero(int variable, String errorMessage) {

        if(variable <= 0) {

            Logger.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }



}
