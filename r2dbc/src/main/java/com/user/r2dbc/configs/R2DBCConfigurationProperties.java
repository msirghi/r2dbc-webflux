package com.user.r2dbc.configs;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "r2dbc")
@Getter
@Setter
public class R2DBCConfigurationProperties {

  @NotEmpty
  private String url;
  private String user;
  private String password;
}
