package com.example.demo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private EntityManager repository;

    @PostMapping("/products")
    @Transactional
    public ResponseEntity<?> save(@RequestBody Product entity) {
        try {
            repository.persist(entity);
        } catch (ConstraintViolationException e) {
            entity.setId(UUID.randomUUID());
            repository.persist(entity);
        }
        return ResponseEntity.ok().build();
    }
}