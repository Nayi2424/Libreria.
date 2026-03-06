package com.Alura.Literatura;

import com.Alura.Literatura.principal.Principal;
import com.Alura.Literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

    @Autowired
    private LibroRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Aquí es donde le decimos: "En cuanto prendas, usa mi clase Principal"
        Principal principal = new Principal(repository);
        principal.muestraElMenu();
    }
}