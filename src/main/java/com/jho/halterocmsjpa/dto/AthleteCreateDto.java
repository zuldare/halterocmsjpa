package com.jho.halterocmsjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AthleteCreateDto {

  private Integer id;

  private String nameSurname;
}
