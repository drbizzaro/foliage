package org.foilage.address.format;

import org.foilage.address.Address;

public interface AddressFormat {

    public String formatAddress(Address address);

    public String formatAddressWhenSentFromAbroad(Address address);

}