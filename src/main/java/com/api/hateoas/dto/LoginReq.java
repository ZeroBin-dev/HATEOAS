package com.api.hateoas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReq {
  private String id;
  private String password;
}
