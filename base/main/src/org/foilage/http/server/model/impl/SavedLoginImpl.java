package org.foilage.http.server.model.impl;

import org.foilage.http.server.model.SavedLogin;

import java.time.LocalDateTime;

import static org.foilage.utils.checkers.GreaterThenZeroChecker.greaterThenZero;
import static org.foilage.utils.checkers.NullChecker.notNull;

public class SavedLoginImpl implements SavedLogin {

    private final int id;

    private final int userId;

    private final String loginHash;

    private final LocalDateTime validFrom;

    private final LocalDateTime validTo;

    private final String loginIp;

    public SavedLoginImpl(int userId, String loginHash, LocalDateTime validFrom, LocalDateTime validTo, String loginIp) {
        this.id = 0;
        this.userId = greaterThenZero(userId);
        this.loginHash = notNull(loginHash);
        this.validFrom = notNull(validFrom);
        this.validTo = notNull(validTo);
        this.loginIp = notNull(loginIp);
    }

    public SavedLoginImpl(int id, int userId, String loginHash, LocalDateTime validFrom, LocalDateTime validTo, String loginIp) {
        this.id = greaterThenZero(id);
        this.userId = greaterThenZero(userId);
        this.loginHash = notNull(loginHash);
        this.validFrom = notNull(validFrom);
        this.validTo = notNull(validTo);
        this.loginIp = notNull(loginIp);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getLoginHash() {
        return loginHash;
    }

    @Override
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    @Override
    public LocalDateTime getValidTo() {
        return validTo;
    }

    @Override
    public String getLoginIp() {
        return loginIp;
    }
}
