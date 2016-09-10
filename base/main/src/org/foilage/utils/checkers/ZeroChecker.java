package org.foilage.utils.checkers;

import org.pmw.tinylog.Logger;

public class ZeroChecker {


    public static int notZero(int variable) {

        return notZero(variable, "Int can't be zero");
    }

    public static int notZero(int variable, String errorMessage) {

        if(variable == 0) {

            Logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static long notZero(long variable) {

        return notZero(variable, "Long can't be zero");
    }

    public static long notZero(long variable, String errorMessage) {

        if(variable == 0) {

            Logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static float notZero(float variable) {

        return notZero(variable, "Float can't be zero");
    }

    public static float notZero(float variable, String errorMessage) {

        if(variable == 0) {

            Logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

    public static double notZero(double variable) {

        return notZero(variable, "Float can't be zero");
    }

    public static double notZero(double variable, String errorMessage) {

        if(variable == 0) {

            Logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }
}
