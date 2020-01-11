package com.jho.halterocmsjpa.dto.athlete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AthleteDto {

    private Integer id;
    private String name;
    private String surname;
    private Integer gender;
    private Integer birthYear;
}
