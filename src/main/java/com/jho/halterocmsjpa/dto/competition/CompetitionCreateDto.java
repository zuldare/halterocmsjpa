package com.jho.halterocmsjpa.dto.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionCreateDto {

    private String place;

    @NotNull
    private String description;

    @NotNull
    private String organizer;

    private Long beginDate;

    private Long endDate;

}
