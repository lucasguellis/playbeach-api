package com.pbio.playbeach.services;

import com.pbio.playbeach.entities.Tournament;
import com.pbio.playbeach.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public List<Tournament> getTournamentsByNameContaining(String name) {
        return tournamentRepository.findByNameContainingIgnoreCase(name);
    }
}
