package com.jho.halterocmsjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOUTS")
public class Bout {

    @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIME)
    @Column(name = "begin_time")
    private LocalTime beginTime;

    @Column(name = "gender")
    private Integer gender;

    @ManyToOne
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    @OneToMany(mappedBy = "bout")
    private Set<AthleteBout> athletesBouts;

    @Transient
    private Integer numberOfLifts;

    @Transient
    private Boolean areSnatchesFinished;

    @Transient
    private Boolean areCJFinished;


}
