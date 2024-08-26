package com.api.hateoas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JoinRes {
  private boolean isLogin;
  private String id;
}
