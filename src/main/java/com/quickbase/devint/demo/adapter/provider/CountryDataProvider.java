package com.quickbase.devint.demo.adapter.provider;

import com.quickbase.devint.demo.core.domain.CountryData;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CountryDataProvider {
    CompletableFuture<List<CountryData>> countryPopulation();
}
