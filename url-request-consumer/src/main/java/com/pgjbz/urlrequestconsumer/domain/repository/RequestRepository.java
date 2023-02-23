package com.pgjbz.urlrequestconsumer.domain.repository;

import com.pgjbz.urlrequestconsumer.domain.model.Request;

public interface RequestRepository {
    void save(final Request request);
}
