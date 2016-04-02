package org.foilage.address;

public enum AddressType {

    PERMANENT(1), TEMPORARY(10), UNKNOWN(0);

    private final int id;

    AddressType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static AddressType getById(int id) {

        for(AddressType type: AddressType.values()) {

            if(id == type.getId()) {

                return type;
            }
        }

        throw new IllegalArgumentException("No address type exists with id "+id);
    }
}
