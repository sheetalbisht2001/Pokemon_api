package com.pokemonreview.api.models;

import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.NoArgsConstructor;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data ;




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


}
