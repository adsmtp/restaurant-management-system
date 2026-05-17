package com.parra.gestion_restaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class GestionRestauranteApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionRestauranteApplication.class, args);
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Hash elpedre: " + encoder.encode("elpedre"));
        
    }
}

//spring-boot:run
// http://localhost:8080