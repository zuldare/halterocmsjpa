package com.jho.halterocmsjpa.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "competitions")
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
}
