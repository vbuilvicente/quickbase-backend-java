package com.quickbase.devint.demo.adapter.repository;

import com.quickbase.devint.demo.adapter.provider.CountryDatabaseProvider;
import com.quickbase.devint.demo.adapter.provider.CountryExternalProvider;
import com.quickbase.devint.demo.adapter.util.CountryNameNormalizer;
import com.quickbase.devint.demo.core.domain.CountryData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryDataRepositoryTest {

    @Mock
    private CountryDatabaseProvider countryDatabaseProvider;

    @Mock
    private CountryExternalProvider countryExternalProvider;

    @InjectMocks
    private CountryDataRepositoryImpl countryDataRepository;

    @Test
    void when_DuplicatedCountryData_then_DbProviderDataTaken() {
        CountryData usaFromDB = createCountryData("USA", 309349689);
        CountryData usaFromExternal = createCountryData("USA", 309349610);
        CountryData canadaFromExternal = createCountryData("Canada", 309349680);

        when(countryDatabaseProvider.countryPopulation())
                .thenReturn(CompletableFuture.completedFuture(List.of(usaFromDB)));

        when(countryExternalProvider.countryPopulation())
                .thenReturn(CompletableFuture.completedFuture(List.of(usaFromExternal, canadaFromExternal)));

        List<CountryData> countryData = countryDataRepository.countryPopulation();

        assertAll(
                () -> assertEquals(2, countryData.size()),
                () -> assertEquals(usaFromDB.getCountry(), countryData.get(0).getCountry()),
                () -> assertEquals(usaFromDB.getPopulation(), countryData.get(0).getPopulation()),
                () -> assertEquals(canadaFromExternal.getCountry(), countryData.get(1).getCountry()),
                () -> assertEquals(canadaFromExternal.getPopulation(), countryData.get(1).getPopulation())
        );
    }

    private CountryData createCountryData(String name, Integer population) {
        return CountryData.builder()
                .country(CountryNameNormalizer.normalizeCountryName(name))
                .population(population)
                .build();
    }
}
