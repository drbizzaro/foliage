package org.foilage.utils;

import java.time.LocalDateTime;

import static org.foilage.utils.checkers.NullChecker.notNull;

public enum TimeSourceType {

    SYSTEM(1, LocalDateTime.now()), EMULATED_STATIC(2, LocalDateTime.now())
    ;

    private final int id;

    private LocalDateTime emulatedTime;

    TimeSourceType(int id, LocalDateTime emulatedTime) {

        this.id = id;
        this.emulatedTime = notNull(emulatedTime);
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getEmulatedTime() {
        return emulatedTime;
    }

    public void setEmulatedTime(LocalDateTime emulatedTime) {
        this.emulatedTime = emulatedTime;
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
