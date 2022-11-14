package com.service.shopPhone.utility;

import java.util.UUID;

public class RequestCorrelation {
    private static final InheritableThreadLocal<String> requestId = new InheritableThreadLocal<>();
  
    private RequestCorrelation() {}
  
    /**
     * Get request ID of current request
     *
     * @return Request ID
     */
    public static String getRequestId() {
      if (requestId.get() == null) {
        return UUID.randomUUID().toString();
      }
  
      return requestId.get();
    }
  
    /**
     * Set request ID, thread safe
     *
     * @param id Request ID
     */
    public static void setRequestId(String id) {
      requestId.set(id);
    }
  }
  