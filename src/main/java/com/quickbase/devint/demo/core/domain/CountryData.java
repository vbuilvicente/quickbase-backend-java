package com.quickbase.devint.demo.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CountryData {

    private String country;
    private Integer population;
}
