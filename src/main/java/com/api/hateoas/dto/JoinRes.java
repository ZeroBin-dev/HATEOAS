package com.api.hateoas.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class JoinRes extends BaseRes {
  private boolean isJoin;
  private String id;
}
