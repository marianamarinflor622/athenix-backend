package com.athenix.athenix.controller;

import com.athenix.athenix.model.ResourceContent;
import com.athenix.athenix.service.ResourceContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceContentController {

    @Autowired
    private ResourceContentService resourceService;

    @GetMapping
    public List<ResourceContent> getAllResources() {
        return resourceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceContent> getResourceById(@PathVariable Long id) {
        return resourceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResourceContent> createResource(@RequestBody ResourceContent resource) {
        ResourceContent saved = resourceService.save(resource);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceContent> updateResource(@PathVariable Long id, @RequestBody ResourceContent resource) {
        try {
            ResourceContent updated = resourceService.update(id, resource);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        resourceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
