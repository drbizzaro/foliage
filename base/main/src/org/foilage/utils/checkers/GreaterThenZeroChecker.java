package org.foilage.utils.checkers;

import org.foilage.utils.log.Log;

public class GreaterThenZeroChecker {

    public static int greaterThenZero(int variable) {

        return greaterThenZero(variable, "Int can't be zero or negative.");
    }

    public static int greaterThenZero(int variable, String errorMessage) {

        if(variable <= 0) {

            Log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static float greaterThenZero(float variable, String errorMessage) {

        if(variable <= 0) {

            Log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static long greaterThenZero(long variable, String errorMessage) {

        if(variable <= 0) {

            Log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static double greaterThenZero(double variable, String errorMessage) {

        if(variable <= 0) {

            Log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

}
