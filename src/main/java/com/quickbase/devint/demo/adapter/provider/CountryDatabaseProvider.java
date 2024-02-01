package com.quickbase.devint.demo.adapter.provider;

import com.quickbase.devint.demo.adapter.db.repository.CountryRepository;
import com.quickbase.devint.demo.adapter.util.CountryNameNormalizer;
import com.quickbase.devint.demo.core.domain.CountryData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class CountryDatabaseProvider implements CountryDataProvider {

    private final CountryRepository countryRepository;

    @Override
    public CompletableFuture<List<CountryData>> countryPopulation() {
        return CompletableFuture.supplyAsync(()-> countryRepository.getCountriesPopulation()
                .stream()
                .map(countryPopulation -> createCountryData(countryPopulation.getName(), countryPopulation.getPopulation()))
                .toList());
    }

    private CountryData createCountryData(String name, Integer population) {
        return CountryData.builder()
                .country(CountryNameNormalizer.normalizeCountryName(name))
                .population(population)
                .build();
    }
}
