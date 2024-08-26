package com.api.hateoas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinReq {
  private String id;
  private String name;
  private String password;
}
