package com.jho.halterocmsjpa.dto.athlete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AthleteCreateDto {

  private String name;
  private String surname;
  private Integer gender;
  private Integer birthYear;

}
