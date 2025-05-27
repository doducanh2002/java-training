package org.aibles.feature.customer.business.constant;

import static org.aibles.feature.customer.business.constant.CustomerApiConstant.ApiConstant.API_PREFIX;
import static org.aibles.feature.customer.business.constant.CustomerApiConstant.ApiConstant.API_VERSION;
import static org.aibles.feature.customer.business.constant.CustomerApiConstant.ResourceConstant.CUSTOMERS;

public class CustomerApiConstant {

  public static class ApiConstant {
    public static final String API_PREFIX = "/api";
    public static final String API_VERSION = "/v1";
  }

  public static class ResourceConstant { ;
    public static final String CUSTOMERS = "/customers";
  }

  public static class BaseUrl {

    public static final String CUSTOMERS_URL = API_PREFIX + API_VERSION + CUSTOMERS;

  }
}
