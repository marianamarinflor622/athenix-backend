package com.athenix.athenix.service;

import com.athenix.athenix.model.Content;
import com.athenix.athenix.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    public Optional<Content> findById(Long id) {
        return contentRepository.findById(id);
    }

    public Content save(Content content) {
        return contentRepository.save(content);
    }

    public void deleteById(Long id) {
        contentRepository.deleteById(id);
    }

    public Content update(Long id, Content updatedContent) {
        return contentRepository.findById(id)
                .map(existingContent -> {
                    existingContent.setTitle(updatedContent.getTitle());
                    existingContent.setDescription(updatedContent.getDescription());
                    existingContent.setCategory(updatedContent.getCategory());
                    return contentRepository.save(existingContent);
                })
                .orElseThrow(() -> new RuntimeException("Content not found with id: " + id));
    }
}
