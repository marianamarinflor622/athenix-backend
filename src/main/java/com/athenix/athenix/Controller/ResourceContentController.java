package com.athenix.athenix.controller;

import com.athenix.athenix.model.Content;
import com.athenix.athenix.model.ResourceContent;
import com.athenix.athenix.service.ResourceContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/resources")
public class ResourceContentController {

    @Autowired
    private ResourceContentService resourceService;

    
    @org.springframework.beans.factory.annotation.Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping
    public java.util.List<ResourceContent> getAllResources() {
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

   
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam("contentId") Long contentId,
                                        @RequestParam("resourceType") String resourceType) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Archivo vacío");
            }

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath);

            ResourceContent resource = new ResourceContent();
            resource.setResourceName(filename);
            resource.setResourceType(resourceType);
            resource.setUrl("/api/resources/download/" + filename);

            Content content = resourceService.getContentById(contentId);
            resource.setContent(content);

            ResourceContent saved = resourceService.save(resource);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al subir archivo");
        }
    }

   
    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
