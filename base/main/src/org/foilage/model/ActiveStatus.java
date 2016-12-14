package org.foilage.model;

public enum ActiveStatus {

    SETUP(0), FULL_ACTIVE(1), VIEW_ACTIVE(2), PAUSED(5), INACTIVE(9);

    private final int id;

    ActiveStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ActiveStatus getById(int id) {

        for(ActiveStatus status: ActiveStatus.values()) {

            if(status.getId()==id) {

                return status;
            }
        }

        throw new IllegalArgumentException("No active status exists with id "+id);
    }

}
