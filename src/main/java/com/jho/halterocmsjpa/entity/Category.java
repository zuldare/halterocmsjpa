package com.jho.halterocmsjpa.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "description")
  private String description;

  @Column(name = "short_description")
  private String shortDescription;

  @Column(name = "initial_weight")
  @Digits(integer = 3, fraction = 2)
  private BigDecimal initialWeight;

  @Column(name = "final_weight")
  @Digits(integer = 3, fraction = 2)
  private Double finalWeight;

  @Column(name = "gender")
  private Integer gender;


}
