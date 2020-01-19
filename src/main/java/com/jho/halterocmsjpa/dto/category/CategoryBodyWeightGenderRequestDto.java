package com.jho.halterocmsjpa.dto.category;

import com.jho.halterocmsjpa.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO in order to request a category based on gender and bodyweight.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBodyWeightGenderRequestDto {

    @NotNull
    private GenderType genderType;

    @NotNull
    @Max(300)
    private BigDecimal bodyweight;
}
