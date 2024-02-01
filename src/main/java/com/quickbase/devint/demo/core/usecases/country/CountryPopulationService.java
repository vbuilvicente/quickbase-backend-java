package com.quickbase.devint.demo.core.usecases.country;

import com.quickbase.devint.demo.core.domain.CountryData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryPopulationService {

    private final CountryDataRepository countryDataRepository;

    public List<CountryData> countryPopulation() {
        return countryDataRepository.countryPopulation();
    }
}
