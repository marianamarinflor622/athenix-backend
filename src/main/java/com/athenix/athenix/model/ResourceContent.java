package com.athenix.athenix.model;

import jakarta.persistence.*;
import lombok.*;

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

    private String type; // tipo lógico: "Web" o "Lab" (del frontend)

    @Column(length = 1000)
    private String description;

    private String resourceName; // nombre real del archivo en disco

    private String resourceType; // tipo físico: image, video, etc.

    private String url; // ruta para descargar

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;
}
