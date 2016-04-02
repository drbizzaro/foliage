package org.foilage.address;

import java.util.Date;

import static org.foilage.utils.checkers.NotNegativeChecker.notNegative;
import static org.foilage.utils.checkers.NullChecker.notNull;

public class AddressImpl implements Address {

    private final int id;

    private final String addressee;

    private final String careOf;

    private final String streetAddress;

    private final String postalCode;

    private final String city;

    private final Country country;

    private final AddressType type;

    private final long validFrom;

    private final long validTo;

    public static void main(String[] args) {

        Address address = new AddressImpl("Fredrik Gustavsson", "Manuela", "Tyringegatan 348", "25276", "Helingborg", Country.SWEDEN, AddressType.PERMANENT, new Date(), new Date());

        System.out.println(address.getCountry().getAddressFormat().formatAddress(address));
    }

    public AddressImpl(String addressee, String careOf, String streetAddress, String postalCode, String city, Country country, AddressType type, Date validFrom, Date validTo) {

        this(0, addressee, careOf, streetAddress, postalCode, city, country, type, validFrom, validTo);
    }

    public AddressImpl(int id, String addressee, String careOf, String streetAddress, String postalCode, String city, Country country, AddressType type, Date validFrom, Date validTo) {
        this.id = notNegative(id, "AddressImpl.id can't be negative!");
        this.addressee = notNull(addressee, "AddressImpl.addressee can't be null!");
        this.careOf = notNull(careOf, "AddressImpl.careOf can't be null!");
        this.streetAddress = notNull(streetAddress, "AddressImpl.streetAddress can't be null!");
        this.postalCode = notNull(postalCode, "AddressImpl.postalCode can't be null!");
        this.city = notNull(city, "AddressImpl.city can't be null!");
        this.country = notNull(country, "AddressImpl.country can't be null!");
        this.type = notNull(type, "AddressImpl.type can't be null!");
        this.validFrom = notNull(validFrom.getTime(), "AddressImpl.validFrom can't be null!");
        this.validTo = notNull(validTo.getTime(), "AddressImpl.validTo can't be null!");

        if(!country.getPostalCodeValidator().isValid(postalCode)) {

            throw new IllegalArgumentException("AddressImpl with id "+id+". PostalCode "+postalCode+" isn't valid for country "+country.name());
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getAddressee() {
        return addressee;
    }

    @Override
    public String getCareOf() {
        return careOf;
    }

    @Override
    public String getStreetAddress() {
        return streetAddress;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public Country getCountry() {
        return country;
    }

    @Override
    public AddressType getType() {
        return type;
    }

    @Override
    public Date getValidFrom() {
        return new Date(validFrom);
    }

    @Override
    public Date getValidTo() {
        return new Date(validTo);
    }
}
