package com.quickbase.devint.demo.application.controller;

import com.quickbase.devint.demo.application.contract.CountryDataResponse;
import com.quickbase.devint.demo.core.domain.CountryData;
import com.quickbase.devint.demo.core.usecases.country.CountryPopulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country/population")
@RequiredArgsConstructor
public class CountryPopulationController {

    private final CountryPopulationService countryPopulationService;

    @GetMapping
    public ResponseEntity<CountryDataResponse> countryPopulation() {
        List<CountryData> countryData = countryPopulationService.countryPopulation();

        return ResponseEntity.ok(new CountryDataResponse(countryData));
    }
}
