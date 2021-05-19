package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> findWhiskiesFilterByYear(
        @RequestParam(name="year", required = false) Integer year,
        @RequestParam(name="distilleryName", required = false) String distilleryName,
        @RequestParam(name="age", required = false) Integer age
        ) {
        if (distilleryName != null && age != null) {
            return new ResponseEntity<>(
                    whiskyRepository.findWhiskiesByDistilleryNameAndAge(distilleryName, age),
                    HttpStatus.OK
            );
        }

        if (year != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/distilleries")
    public ResponseEntity<List<Whisky>> findWhiskiesByQueryDistilleryRegion(
            @RequestParam(name="region") String region) {
        return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryRegion(region), HttpStatus.OK);


    }



}
