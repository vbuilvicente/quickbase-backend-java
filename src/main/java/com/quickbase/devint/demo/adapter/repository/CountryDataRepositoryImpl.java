package com.quickbase.devint.demo.adapter.repository;

import com.quickbase.devint.demo.adapter.provider.CountryDatabaseProvider;
import com.quickbase.devint.demo.adapter.provider.CountryExternalProvider;
import com.quickbase.devint.demo.core.domain.CountryData;
import com.quickbase.devint.demo.core.usecases.country.CountryDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CountryDataRepositoryImpl implements CountryDataRepository {

    private final CountryDatabaseProvider countryDatabaseProvider;
    private final CountryExternalProvider countryExternalProvider;

    @Override
    public List<CountryData> countryPopulation() {
        CompletableFuture<List<CountryData>> externalCountryPopulation = countryExternalProvider.countryPopulation();
        CompletableFuture<List<CountryData>> dbCountryPopulation = countryDatabaseProvider.countryPopulation();

        CompletableFuture<List<CountryData>> combinedFuture = externalCountryPopulation
                .thenCombine(dbCountryPopulation, (externalData, databaseData) -> {
                    List<CountryData> combineData = new ArrayList<>();
                    Map<String, CountryData> mapDbData = databaseData
                            .stream()
                            .collect(Collectors.toMap(CountryData::getCountry, Function.identity()));
                    for (CountryData externalCountry : externalData) {
                        combineData.add(mapDbData.getOrDefault(externalCountry.getCountry(), externalCountry));
                    }
                    return combineData;
                });

        return combinedFuture.join();
    }
}
