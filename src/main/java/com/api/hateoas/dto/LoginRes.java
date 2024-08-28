package com.api.hateoas.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class LoginRes extends BaseRes {
  private boolean isLogin;
  private String id;
}
