package com.quickbase.devint.demo.adapter.db.repository;

import com.quickbase.devint.demo.adapter.db.entities.Country;
import com.quickbase.devint.demo.adapter.db.repository.projection.CountryPopulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(
            """ 
            SELECT a.name as name, SUM(c.population) as population
            FROM Country a
            JOIN a.states s
            JOIN s.cities c
            GROUP BY a.id
            """
    )
    List<CountryPopulation> getCountriesPopulation();
}
