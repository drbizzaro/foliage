package org.foilage.address.format;

import org.pmw.tinylog.Logger;

public class PostalCodeFormatter_NNN_NN implements PostalCodeFormatter {

    @Override
    public String formatPostalCode(String postalCode) {

        try {

            StringBuilder formattedString = new StringBuilder();

            formattedString.append(postalCode.substring(0, 3));
            formattedString.append(" ");
            formattedString.append(postalCode.substring(3));

            return formattedString.toString();

        } catch (Exception e) {

            Logger.info("PostalCodeFormatter_NNN_NN.formatPostalCode error for postalCode "+postalCode+": "+e.getMessage());

            return postalCode;
        }
    }
}
