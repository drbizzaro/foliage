package org.foilage.address;

public enum DegreeOfTrust {

    UNKNOWN(0), NO_TRUST(1), LOW_TRUST(2), HIGH_TRUST(10);

    private final int id;

    DegreeOfTrust(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DegreeOfTrust getById(int id) {

        for(DegreeOfTrust trust: DegreeOfTrust.values()) {

            if(trust.getId()==id) {

                return trust;
            }
        }

        throw new IllegalArgumentException("No org.foilage.address.DegreeOfTrust exists with id "+id);
    }

}
