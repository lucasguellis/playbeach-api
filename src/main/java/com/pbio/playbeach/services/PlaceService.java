package com.pbio.playbeach.services;

import com.pbio.playbeach.entities.Place;
import com.pbio.playbeach.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Optional<Place> getPlaceById(Long id) {
        return placeRepository.findById(id);
    }

    public Optional<Place> getPlaceByEmail(String email) {
        return placeRepository.findByEmail(email);
    }

    public List<Place> getPlacesByNameContaining(String name) {
        return placeRepository.findByNameContainingIgnoreCase(name);
    }

    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }

    public void deletePlace(Long id) {
        placeRepository.deleteById(id);
    }
}
