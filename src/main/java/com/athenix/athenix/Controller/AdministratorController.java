package com.athenix.athenix.controller;

import com.athenix.athenix.model.Administrator;
import com.athenix.athenix.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdministratorController {

    @Autowired
    private AdministratorService adminService;

    @GetMapping
    public List<Administrator> getAllAdmins() {
        return adminService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrator> getAdminById(@PathVariable Long id) {
        return adminService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Administrator> createAdmin(@RequestBody Administrator admin) {
        Administrator saved = adminService.save(admin);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrator> updateAdmin(@PathVariable Long id, @RequestBody Administrator admin) {
        try {
            Administrator updated = adminService.update(id, admin);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
