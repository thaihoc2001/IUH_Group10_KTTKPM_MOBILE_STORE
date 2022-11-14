package com.service.shopPhone.models;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseListEntityResponseModel {

  private int totalPages;
  private int pageSize;
  private long totalItems;
  private int currentPage;
}

