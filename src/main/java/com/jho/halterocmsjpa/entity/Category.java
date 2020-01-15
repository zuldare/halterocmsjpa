package com.jho.halterocmsjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

/**
 * Category entity layer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CATEGORIES")
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
  private BigDecimal finalWeight;

  @Column(name = "gender")
  private Integer gender;


}
