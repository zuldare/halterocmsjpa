package com.jho.halterocmsjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMPETITIONS")
public class Competition {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "place")
  @Size(max = 128)
  private String place;

  @Column(name = "organizer")
  @Size(max = 128)
  private String organizer;

  @Temporal(TemporalType.DATE)
  @Column(name = "begin")
  private Date beginDate;

  @Temporal(TemporalType.DATE)
  @Column(name = "end")
  private Date endDate;

  @OneToMany(mappedBy = "competition")
  private Set<Bout> bouts;

}
