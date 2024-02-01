package com.quickbase.devint.demo.adapter.db.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "City")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {

    @Id
    @Column(name = "CityId")
    private Long id;

    @Column(name = "CityName")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "StateId")
    private State state;

    @Column(name = "Population")
    private Long population;
}
