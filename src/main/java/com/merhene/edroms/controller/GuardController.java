package com.merhene.edroms.controller;

import com.merhene.edroms.model.Guard;
import org.springframework.beans.factory.annotation.Autowired;
import com.merhene.edroms.repository.GuardRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/guard")
public class GuardController {

    private final GuardRepository guardRepository;

    public GuardController(GuardRepository guardRepository) {
        this.guardRepository = guardRepository;
    }

    @GetMapping
    public ResponseEntity<List<Guard>> getAllArticles() {
        List<Guard> guards = guardRepository.findAll();
        if (guards.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(guards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guard> getGuardById(@PathVariable Long id) {
        Guard guard = guardRepository.findById(id).orElse(null);
        if (guard == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(guard);
    }

    @PostMapping
    public ResponseEntity<Guard> createGuard(@RequestBody Guard guard) {
        guard.setType(guard.getType());
        guard.setStartDate(guard.getStartDate());
        guard.setEndDate(guard.getEndDate());
        guard.setComment(guard.getComment());
        guard.setCreatedAt(LocalDateTime.now());
        guard.setUpdatedAt(LocalDateTime.now());
        Guard savedGuard = guardRepository.save(guard);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGuard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guard> updateGuard(@PathVariable Long id, @RequestBody Guard guardDetails) {

        Guard guard = guardRepository.findById(id).orElse(null);
        if (guard == null) {
            return ResponseEntity.notFound().build();
        }

        guard.setType(guardDetails.getType());
        guard.setStartDate(guardDetails.getStartDate());
        guard.setEndDate(guardDetails.getEndDate());
        guard.setComment(guardDetails.getComment());
        guard.setUsers(guardDetails.getUsers());
        guard.setUpdatedAt(LocalDateTime.now());

        Guard updatedGuard = guardRepository.save(guard);
        return ResponseEntity.ok(updatedGuard);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuard(@PathVariable Long id) {

        Guard guard = guardRepository.findById(id).orElse(null);
        if (guard == null) {
            return ResponseEntity.notFound().build();
        }

        guardRepository.delete(guard);
        return ResponseEntity.noContent().build();
    }

}
