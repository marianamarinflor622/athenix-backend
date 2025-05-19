package com.athenix.athenix.service;

import com.athenix.athenix.model.ResourceContent;
import com.athenix.athenix.repository.ResourceContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceContentService {

    @Autowired
    private ResourceContentRepository resourceContentRepository;

    public List<ResourceContent> findAll() {
        return resourceContentRepository.findAll();
    }

    public Optional<ResourceContent> findById(Long id) {
        return resourceContentRepository.findById(id);
    }

    public ResourceContent save(ResourceContent resourceContent) {
        return resourceContentRepository.save(resourceContent);
    }

    public void deleteById(Long id) {
        resourceContentRepository.deleteById(id);
    }

    // <-- Agrega este método:
    public ResourceContent update(Long id, ResourceContent updatedResource) {
        return resourceContentRepository.findById(id)
            .map(existing -> {
                existing.setResourceName(updatedResource.getResourceName());
                existing.setResourceType(updatedResource.getResourceType());
                existing.setUrl(updatedResource.getUrl());
                existing.setContent(updatedResource.getContent());
                return resourceContentRepository.save(existing);
            })
            .orElseThrow(() -> new RuntimeException("ResourceContent not found with id: " + id));
    }
}
