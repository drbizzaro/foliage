package org.foilage.address.format;

import org.foilage.address.Address;

public class AddressFormatSwedish extends AbstractAddressFormat {

    private final PostalCodeFormatter postalCodeFormatter = new PostalCodeFormatter_NNN_NN();

    @Override
    public String formatAddress(Address address) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(address.getAddressee());
        stringBuilder.append("\n");

        if(address.getCareOf().length()>0) {

            stringBuilder.append(address.getCareOf());
            stringBuilder.append("\n");
        }

        stringBuilder.append(address.getStreetAddress());
        stringBuilder.append("\n");

        stringBuilder.append(postalCodeFormatter.formatPostalCode(address.getPostalCode()));
        stringBuilder.append(" ");
        stringBuilder.append(address.getCity());
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    @Override
    public String formatAddressWhenSentFromAbroad(Address address) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(address.getAddressee());
        stringBuilder.append("\n");

        if(address.getCareOf().length()>0) {

            stringBuilder.append(address.getCareOf());
            stringBuilder.append("\n");
        }

        stringBuilder.append(address.getStreetAddress());
        stringBuilder.append("\n");

        stringBuilder.append(address.getCountry().getAlpha2Code());
        stringBuilder.append("-");
        stringBuilder.append(postalCodeFormatter.formatPostalCode(address.getPostalCode()));
        stringBuilder.append(address.getCity());
        stringBuilder.append("\n");

        stringBuilder.append(address.getCountry().name());

        return stringBuilder.toString();
    }



}
