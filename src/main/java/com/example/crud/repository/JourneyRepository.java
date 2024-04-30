package com.example.crud.repository;

import com.example.crud.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneyRepository extends JpaRepository<Journey,Long> {
}
