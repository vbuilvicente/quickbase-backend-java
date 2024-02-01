package com.quickbase.devint.demo.application.controller;

import com.quickbase.devint.demo.adapter.util.CountryNameNormalizer;
import com.quickbase.devint.demo.core.domain.CountryData;
import com.quickbase.devint.demo.core.usecases.country.CountryPopulationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(CountryPopulationController.class)
class CountryPopulationControllerTest {

    private static final String BASE_URL = "/api/v1/country/population";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryPopulationService countryPopulationService;

    @Test
    void when_ValidRequest_then_CountryData() throws Exception {
        CountryData usa = createCountryData("USA", 309349689);
        CountryData canada = createCountryData("Canada", 309349680);

        when(countryPopulationService.countryPopulation())
                .thenReturn(List.of(usa, canada));

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryPopulations.length()").value(2))
                .andExpect(jsonPath("$.countryPopulations.[0].country").value(usa.getCountry()))
                .andExpect(jsonPath("$.countryPopulations.[0].population").value(usa.getPopulation()));
    }

    private CountryData createCountryData(String name, Integer population) {
        return CountryData.builder()
                .country(CountryNameNormalizer.normalizeCountryName(name))
                .population(population)
                .build();
    }
}
