package com.pgjbz.urlrequestconsumer.domain.repository;

public interface RequestRepository {
    void save(final String headers, Long urlId);
}
