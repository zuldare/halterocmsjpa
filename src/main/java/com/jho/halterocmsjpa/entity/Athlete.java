package com.jho.halterocmsjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ATHLETES")
public class Athlete {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "gender")
  private Integer gender;

  @Column(name = "birth_year")
  private Integer birthYear;

  @OneToMany(mappedBy = "athlete")
  private Set<AthleteBout> athletesBouts;

}
