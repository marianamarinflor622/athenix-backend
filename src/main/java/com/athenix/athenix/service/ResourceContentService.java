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

    public ResourceContent save(ResourceContent resource) {
        return resourceContentRepository.save(resource);
    }

    public void deleteById(Long id) {
        resourceContentRepository.deleteById(id);
    }
}
