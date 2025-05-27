package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.InternalErrorException;

public class EmailDeliveryException extends InternalErrorException {

  public EmailDeliveryException() {
    setCode("org.aibles.feature.customer.business.exception.EmailDeliveryException");
  }

}
