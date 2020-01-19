package com.jho.halterocmsjpa.dto.bout;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jho.halterocmsjpa.dto.competition.CompetitionDto;

import java.util.Date;

/**
 * Dto for bout.
 */
public class BoutDto {

    private Integer id;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    private String gender;

    @JsonIgnore
    private CompetitionDto competition;

    private Integer numberOfLifts;
    private Boolean areSnatchesFinished;
    private Boolean areCJFinished;
    /*
  @OneToMany(mappedBy = "bout", cascade = CascadeType.ALL)
  private Set<AthleteBout> athletesBouts;
*/
}
