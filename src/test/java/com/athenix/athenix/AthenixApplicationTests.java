package com.athenix.athenix;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
@SpringBootTest
@ActiveProfiles("test") // Asegúrate de tener un perfil de prueba configurado
class BackendApplicationTests {
    @Test
    void contextLoads() {
        // Este test simplemente verifica que el contexto de Spring se carga
        // correctamente
    }
}