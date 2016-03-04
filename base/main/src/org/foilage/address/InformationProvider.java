package org.foilage.address;

public enum InformationProvider {

    UNKNOWN(0), INDIVIDUAL(1), POPULATION_REGISTER(10);

    private final int id;

    InformationProvider(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
