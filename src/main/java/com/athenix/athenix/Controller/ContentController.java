package com.athenix.athenix.controller;

import com.athenix.athenix.model.Content;
import com.athenix.athenix.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping
    public List<Content> getAllContents() {
        return contentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable Long id) {
        return contentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Content createContent(@RequestBody Content content) {
        return contentService.save(content);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable Long id, @RequestBody Content content) {
        Content updatedContent = contentService.update(id, content);
        if (updatedContent != null) {
            return ResponseEntity.ok(updatedContent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
