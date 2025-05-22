package com.athenix.athenix.service;

import com.athenix.athenix.config.FileStorageProperties;
import com.athenix.athenix.exception.FileStorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);  // crea la carpeta uploads si no existe
        } catch (Exception ex) {
            throw new FileStorageException("No se pudo crear el directorio donde se guardarán los archivos.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalizar nombre archivo
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new FileStorageException("El archivo no tiene un nombre válido.");
        }
        String fileName = StringUtils.cleanPath(originalFileName);

        try {
            // Verificar que el nombre de archivo no contenga caracteres inválidos
            if (fileName.contains("..")) {
                throw new FileStorageException("Nombre de archivo inválido: " + fileName);
            }

            // Copiar archivo a la carpeta uploads (sobrescribe si existe)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("No se pudo almacenar el archivo " + fileName, ex);
        }
    }

    public Path getFilePath(String fileName) {
        return this.fileStorageLocation.resolve(fileName).normalize();
    }
}
