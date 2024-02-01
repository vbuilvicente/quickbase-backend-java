package com.quickbase.devint.demo.adapter.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CountryNameNormalizer {

    private static Map<String, String> countryNameMapping;

    static {
        countryNameMapping = new HashMap<>();
        countryNameMapping.put("usa", "United States");
        countryNameMapping.put("u.s.a.", "United States");
        countryNameMapping.put("united states of america", "United States");
    }

    public static String normalizeCountryName(String input) {
        String lowercaseInput = input.toLowerCase();

        return countryNameMapping.getOrDefault(lowercaseInput, input);
    }
}
