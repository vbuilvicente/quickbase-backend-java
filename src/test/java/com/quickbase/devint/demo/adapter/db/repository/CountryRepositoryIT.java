package com.quickbase.devint.demo.adapter.db.repository;

import com.quickbase.devint.demo.adapter.db.config.IntegrationTestConfig;
import com.quickbase.devint.demo.adapter.db.repository.projection.CountryPopulation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = IntegrationTestConfig.class)
class CountryRepositoryIT {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    void when_CountryData_then_CountryPopulationAggregated() {
        List<CountryPopulation> countryPopulations = countryRepository.getCountriesPopulation();

        assertAll(
                () -> assertEquals(16, countryPopulations.size()),
                () -> assertNotNull(countryPopulations.get(0).getName()),
                () -> assertNotNull(countryPopulations.get(0).getPopulation())
        );
    }
}
