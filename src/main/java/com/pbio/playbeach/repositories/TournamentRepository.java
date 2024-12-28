package com.pbio.playbeach.repositories;

import com.pbio.playbeach.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findByNameContainingIgnoreCase(String name);
}
