package com.jho.halterocmsjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ATHLETE_BOUTS")
public class AthleteBout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sn1_weight")
    private Integer sn1Weight;

    @Column(name = "sn2_weight")
    private Integer sn2Weight;

    @Column(name = "sn3_weight")
    private Integer sn3Weight;

    @Column(name = "sn1_state")
    private Integer sn1State;

    @Column(name = "sn2_state")
    private Integer sn2State;

    @Column(name = "sn3_state")
    private Integer sn3State;

    @Column(name = "cj1_weight")
    private Integer cj1Weight;

    @Column(name = "cj2_weight")
    private Integer cj2Weight;

    @Column(name = "cj3_weight")
    private Integer cj3Weight;

    @Column(name = "cj1_state")
    private Integer cj1State;

    @Column(name = "cj2_state")
    private Integer cj2State;

    @Column(name = "cj3_state")
    private Integer cj3State;

    @Column(name = "olympic_total")
    private Integer olympicTotal;

    @Column(name = "sinclair_points")
    private BigDecimal sinclairPoints;

    @Column(name = "body_weight")
    private BigDecimal bodyWeight;

    @ManyToOne
    @JoinColumn(name = "bout_id", nullable = false)
    private Bout bout;

    @ManyToOne
    @JoinColumn(name = "athlete_id", nullable = false)
    private Athlete athlete;

}
