package com.athenix.athenix.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resource_contents")
public class ResourceContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String type; 

    private String description;

    private String resourceName; 

    private String resourceType; 

    private String url; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "content_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Content content;
}
