package com.api.hateoas.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UpdateRes extends BaseRes {
  private boolean isUpdate;
}
