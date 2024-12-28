package com.pbio.playbeach.controllers;

import com.pbio.playbeach.entities.Place;
import com.pbio.playbeach.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public ResponseEntity<List<Place>> getAllPlaces() {
        List<Place> places = placeService.getAllPlaces();
        return ResponseEntity.ok(places);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable Long id) {
        Optional<Place> place = placeService.getPlaceById(id);
        return place.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-email")
    public ResponseEntity<Place> getPlaceByEmail(@RequestParam String email) {
        Optional<Place> place = placeService.getPlaceByEmail(email);
        return place.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Place>> searchPlacesByName(@RequestParam String name) {
        List<Place> places = placeService.getPlacesByNameContaining(name);
        return ResponseEntity.ok(places);
    }

    @PostMapping
    public ResponseEntity<Place> createPlace(@RequestBody Place place) {
        Place savedPlace = placeService.savePlace(place);
        return ResponseEntity.status(201).body(savedPlace);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Place> updatePlace(@PathVariable Long id, @RequestBody Place placeDetails) {
        Optional<Place> existingPlace = placeService.getPlaceById(id);
        if (existingPlace.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Place placeToUpdate = existingPlace.get();
        placeToUpdate.setName(placeDetails.getName());
        placeToUpdate.setEmail(placeDetails.getEmail());
        placeToUpdate.setLatitude(placeDetails.getLatitude());
        placeToUpdate.setLongitude(placeDetails.getLongitude());
        placeToUpdate.setAddress(placeDetails.getAddress());
        placeToUpdate.setIsActive(placeDetails.getIsActive());

        Place updatedPlace = placeService.savePlace(placeToUpdate);
        return ResponseEntity.ok(updatedPlace);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        if (placeService.getPlaceById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        placeService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }
}
