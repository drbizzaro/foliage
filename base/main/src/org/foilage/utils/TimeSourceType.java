package org.foilage.utils;

public enum TimeSourceType {

    SYSTEM(1);

    private final int id;

    TimeSourceType(int id) {

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TimeSourceType getById(int id) {

        for(TimeSourceType type: TimeSourceType.values()) {

            if(id==type.getId()) {

                return type;
            }
        }

        throw new IllegalArgumentException("No time source type exists with id "+id);
    }

}
