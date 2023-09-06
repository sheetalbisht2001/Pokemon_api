package com.pokemonreview.api.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;


import lombok.AllArgsConstructor;
import lombok.Data ;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name ;
    private String type ;

    @OneToMany(mappedBy = "pokemon" , cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews = new ArrayList<Review>();


}
