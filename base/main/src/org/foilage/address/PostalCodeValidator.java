    package org.foilage.address;

import org.foilage.address.services.PostalCodeStringValidator;
import org.foilage.address.services.PostalCodeStringValidatorImpl;

public enum PostalCodeValidator {

    UNKNOWN(0, new PostalCodeStringValidatorImpl("" ,new String[]{" ", "-"})),
    NUMERIC_3N(1, new PostalCodeStringValidatorImpl("\\d{3}" ,new String[]{" ", "-"})),
    NUMERIC_4N(2, new PostalCodeStringValidatorImpl("\\d{4}" ,new String[]{" ", "-"})),
    NUMERIC_5N(3, new PostalCodeStringValidatorImpl("\\d{5}" ,new String[]{" ", "-"})),
    ALPHANUMERIC(10, new PostalCodeStringValidatorImpl("" ,new String[]{" "}));

    private final int id;

    private final PostalCodeStringValidator stringValidator;

    PostalCodeValidator(int id, PostalCodeStringValidator stringValidator) {

        this.id = id;
        this.stringValidator = stringValidator;
    }

    public int getId() {

        return id;
    }

    public boolean isValid(String postalCode) {

        return stringValidator.isValid(postalCode);
    }

    public PostalCodeStringValidator getStringValidator() {

        return stringValidator;
    }

    public static PostalCodeValidator getById(int id) {

        for(PostalCodeValidator format: PostalCodeValidator.values()) {

            if(format.getId() == id) {

                return format;
            }
        }
        throw new IllegalArgumentException("No postal code format with id "+id+" exists!");
    }

}
