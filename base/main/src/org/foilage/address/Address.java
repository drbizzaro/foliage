package org.foilage.address;

import java.time.LocalDateTime;

public interface Address {

    public int getId();

    public String getAddressee();

    public String getCareOf();

    public String getStreetAddress();

    public String getPostalCode();

    public String getCity();

    public Country getCountry();

    public AddressType getType();

    public LocalDateTime getValidFrom();

    public LocalDateTime getValidTo();
}
