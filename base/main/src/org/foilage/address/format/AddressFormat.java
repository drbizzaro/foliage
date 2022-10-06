package org.foilage.address.format;

import org.foilage.address.Address;

public interface AddressFormat {

    String formatAddress(Address address);

    String formatAddressWhenSentFromAbroad(Address address);

}