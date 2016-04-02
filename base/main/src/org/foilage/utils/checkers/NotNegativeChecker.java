package org.foilage.utils.checkers;

import org.pmw.tinylog.Logger;

public class NotNegativeChecker {

    public static int notNegative(int variable) {

        return notNegative(variable, "Int can't be negative.");
    }

    public static int notNegative(int variable, String errorMessage) {

        if(variable < 0) {

            Logger.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }

        return variable;
    }

}
