package com.example.crud.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Transactional
@AllArgsConstructor
@NoArgsConstructor
@Table(name="passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long passengerId;
    @Column(name="name")
    private String name;
    @Column(name="food")
    private String food;
    @Column(name="berth")
    private String berth;

}
