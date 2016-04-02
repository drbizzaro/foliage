package org.foilage.address;


import org.foilage.address.format.AddressFormat;
import org.foilage.address.format.AddressFormatSwedish;

import static org.foilage.utils.checkers.GreaterThenZeroChecker.greaterThenZero;
import static org.foilage.utils.checkers.NullChecker.notNull;

public enum Country {

    UNKNOWN("en", "  ", "   ", 1, "   ", new AddressFormatSwedish(), PostalCodeValidator.UNKNOWN),

    //DENMARK("da", "DK", "DNK", 208, "DKK", new AddressFormatSwedish(), PostalCodeValidator.NUMERIC_4N),
    //NORWAY("no", "NO", "NOR", 578, "NOK", new AddressFormatSwedish(), PostalCodeValidator.UNKNOWN),
    SWEDEN("sv", "SE", "SWE", 752, "SEK", new AddressFormatSwedish(), PostalCodeValidator.NUMERIC_5N),
    //FINLAND("fi","FI", "FIN", 246, "EUR", new AddressFormatSwedish(), PostalCodeValidator.NUMERIC_5N),
    //ICELAND("is","IS", "ISL", 354, "ISK", new AddressFormatSwedish(), PostalCodeValidator.UNKNOWN),


            ;

    private final String languageCode;

    //ISO 3166-1 alpha-2
    private final String alpha2Code;

    //ISO 3166-1 alpha-3
    private final String alpha3Code;

    //ISO 3166 numeric
    private final int numericCode;

    //ISO 4217 currency code
    private final String currencyCode;

    private final AddressFormat addressFormat;

    private final PostalCodeValidator postalCodeValidator;

    Country(String languageCode, String alpha2Code, String alpha3Code, int numericCode, String currencyCode, AddressFormat addressFormat, PostalCodeValidator postalCodeValidator) {

        this.languageCode = notNull(languageCode, "Country.languageCode can't be null!");
        this.alpha2Code = notNull(alpha2Code, "Country.alpha2Code can't be null!");
        this.alpha3Code = notNull(alpha3Code, "Country.alpha3Code can't be null!");
        this.numericCode = greaterThenZero(numericCode, "Country.numericCode must be greater then zero!");
        this.currencyCode = notNull(currencyCode, "Country.currencyCode can't be null!");
        this.addressFormat = notNull(addressFormat, "Country.AddressFormat can't be null!");
        this.postalCodeValidator = notNull(postalCodeValidator, "Country.postalCodeValidator can't be null!");
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public int getNumericCode() {
        return numericCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public AddressFormat getAddressFormat() {
        return addressFormat;
    }

    public PostalCodeValidator getPostalCodeValidator() {
        return postalCodeValidator;
    }
}
