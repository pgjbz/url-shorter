package com.pgjbz.urlrequestconsumer.domain.service;

import com.pgjbz.urlrequestconsumer.domain.model.Request;

public sealed interface RequestService permits RequestServiceImpl {
    void save(final Request request);
}
