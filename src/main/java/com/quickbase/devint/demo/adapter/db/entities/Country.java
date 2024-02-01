package com.quickbase.devint.demo.adapter.db.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Country")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country {

    @Id
    @Column(name = "CountryId")
    private Long id;

    @Column(name = "CountryName")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "country", cascade = CascadeType.ALL)
    private List<State> states;
}
