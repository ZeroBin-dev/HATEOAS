package com.api.hateoas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateReq {
  private String id;
  private String name;
  private String password;
}
