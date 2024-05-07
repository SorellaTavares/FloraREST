package com.example.florarest.data;

import com.example.florarest.service.Flora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FloraRepository extends JpaRepository<Flora, String> {
    Optional<Flora> findByName(String name);

    void deleteByName(String name);
}
