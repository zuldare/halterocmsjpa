package com.jho.halterocmsjpa.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer id;

    private String description;

    private String shortDescription;

    private BigDecimal initialWeight;

    private BigDecimal finalWeight;

    private Integer gender;
}
