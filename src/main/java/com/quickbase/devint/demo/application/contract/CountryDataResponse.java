package com.quickbase.devint.demo.application.contract;

import com.quickbase.devint.demo.core.domain.CountryData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class CountryDataResponse {
    private List<CountryData> countryPopulations;
}
