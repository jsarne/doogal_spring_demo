package com.example.demo.jpa;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Production;

public interface ProductionRepository extends CrudRepository<Production, Long> {
    Production findById(long id);
}