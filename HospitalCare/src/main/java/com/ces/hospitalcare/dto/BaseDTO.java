package com.ces.hospitalcare.dto;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDTO {
  private Long id;

  private Long createdBy;

  private Long modifiedBy;

  private Date createdDate;

  private Date modifiedDate;
}
