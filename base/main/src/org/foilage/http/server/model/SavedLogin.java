package org.foilage.http.server.model;

import java.time.LocalDateTime;

public interface SavedLogin {

    int getId();

    int getUserId();

    String getLoginHash();

    LocalDateTime getValidFrom();

    LocalDateTime getValidTo();

    String getLoginIp();

}
