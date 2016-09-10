package org.foilage.database;

public class PoolSettings {

    private final int poolMinSize;

    private final int poolMaxSize;

    private final int validationRefreshTimeInSeconds;

    public PoolSettings(int poolMinSize, int poolMaxSize, int validationRefreshTimeInSeconds) {
        this.poolMinSize = poolMinSize;
        this.poolMaxSize = poolMaxSize;
        this.validationRefreshTimeInSeconds = validationRefreshTimeInSeconds;
    }

    public int getPoolMinSize() {
        return poolMinSize;
    }

    public int getPoolMaxSize() {
        return poolMaxSize;
    }

    public int getValidationRefreshTimeInSeconds() {
        return validationRefreshTimeInSeconds;
    }
}
