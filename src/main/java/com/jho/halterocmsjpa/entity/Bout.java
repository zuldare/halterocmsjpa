package com.jho.halterocmsjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Bout entity layer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOUTS")
public class Bout implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "description")
  private String description;

  @Temporal(TemporalType.TIME)
  @Column(name = "begin_time")
  private Date beginTime;

  @Column(name = "gender")
  private Integer gender;

  @ManyToOne
  @JoinColumn(name = "competition_id", nullable = false)
  private Competition competition;

  @OneToMany(mappedBy = "bout", cascade = CascadeType.ALL)
  private Set<AthleteBout> athletesBouts;

  // TODO MOVE TO BOUTDTO
  private Integer numberOfLifts;

  private Boolean areSnatchesFinished;

  private Boolean areCJFinished;
}
