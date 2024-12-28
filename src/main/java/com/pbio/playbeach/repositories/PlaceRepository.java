package com.pbio.playbeach.repositories;

import com.pbio.playbeach.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByEmail(String email);
    Optional<Place> findByName(String name);
    List<Place> findByNameContainingIgnoreCase(String name);
}
