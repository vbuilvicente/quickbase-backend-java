package com.quickbase.devint.demo.adapter.db.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "State")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class State {

    @Id
    @Column(name = "StateId")
    private Long id;

    @Column(name = "StateName")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CountryId")
    private Country country;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "state", cascade = CascadeType.ALL)
    private List<City> cities;
}
