package org.foilage.utils.log;

public enum DebugLevel {

    TRACE(0), DEBUG(1), INFO(10), ERROR(20), FATAL(30);

    private final int level;

    DebugLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
