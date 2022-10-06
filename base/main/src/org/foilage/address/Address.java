package org.foilage.address;

import java.time.LocalDateTime;

public interface Address {

    int getId();

    String getAddressee();

    String getCareOf();

    String getStreetAddress();

    String getPostalCode();

    String getCity();

    Country getCountry();

    AddressType getType();

    LocalDateTime getValidFrom();

    LocalDateTime getValidTo();
}
