package com.quickbase.devint.demo.core.usecases.country;

import com.quickbase.devint.demo.core.domain.CountryData;

import java.util.List;

public interface CountryDataRepository {

    List<CountryData> countryPopulation();
}
