package com.quickbase.devint.demo.adapter.provider;

import com.quickbase.devint.demo.adapter.remote.ConcreteStatService;
import com.quickbase.devint.demo.adapter.util.CountryNameNormalizer;
import com.quickbase.devint.demo.core.domain.CountryData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class CountryExternalProvider implements CountryDataProvider {

    private final ConcreteStatService concreteStatService;

    @Override
    public CompletableFuture<List<CountryData>> countryPopulation() {
        return CompletableFuture.supplyAsync(()-> concreteStatService.GetCountryPopulations()
                 .stream()
                 .map(pair -> createCountryData(pair.getLeft(), pair.getRight()))
                 .toList());
    }

    private CountryData createCountryData(String name, Integer population) {
        return CountryData.builder()
                .country(CountryNameNormalizer.normalizeCountryName(name))
                .population(population)
                .build();
    }
}
