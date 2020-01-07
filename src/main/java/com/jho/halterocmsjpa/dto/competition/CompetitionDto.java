package com.jho.halterocmsjpa.dto.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionDto {

    private Integer id;

    private String description;

    private String place;

    private String organizer;

    private Date beginDate;

    private Date endDate;
}