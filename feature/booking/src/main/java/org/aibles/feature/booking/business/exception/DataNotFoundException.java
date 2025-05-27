package org.aibles.feature.booking.business.exception;

import org.aibles.business.BaseException;

public class DataNotFoundException extends BaseException {
    public DataNotFoundException() {
        setStatus(404);
        setCode("org.aibles.business.exception.DataNotFoundException");
    }
}
