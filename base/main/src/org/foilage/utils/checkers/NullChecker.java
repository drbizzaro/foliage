package org.foilage.utils.checkers;

import org.foilage.utils.log.Log;

import java.util.Collection;

public class NullChecker {

    public static <T> T notNull(T variable) {

        return notNull(variable, "Variable of type "+variable.getClass().getCanonicalName()+" can't be null");
    }

    public static <T> T notNull(T variable, String errorMessage) {

        if(variable == null) {

            Log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if(variable instanceof Collection) {

            childrenNotNull((Collection)variable, errorMessage);

        } else if(variable.getClass().isArray()) {

            try {

                childrenNotNull((Object[])variable, errorMessage);

            } catch (ClassCastException e) {

                Log.debug(e.getMessage());
            }
        }

        return variable;
    }

    private static void childrenNotNull(Collection variable, String errorMessage) {

        try {

            for(Object childObject : variable.toArray()) {

                if(childObject == null) {

                    Log.error(errorMessage);
                    throw new IllegalArgumentException(errorMessage);
                }
            }

        } catch(ClassCastException e) {

            Log.debug(e.getMessage());
        }
    }

    private static void childrenNotNull(Object[] variable, String errorMessage) {

        try {

            for(Object childObject : variable) {

                if(childObject == null) {

                    Log.error(errorMessage);
                    throw new IllegalArgumentException(errorMessage);
                }
            }

        } catch(ClassCastException e) {

            Log.debug(e.getMessage());
        }
    }

}
