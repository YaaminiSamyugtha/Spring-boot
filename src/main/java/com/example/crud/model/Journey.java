package com.example.crud.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Transactional
@AllArgsConstructor
@NoArgsConstructor
@Table(name="journey")
public class Journey {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long journeyId;
    @Column(name="source")
    private String Source;
    @Column(name="destination")
    private String Destination;
}
