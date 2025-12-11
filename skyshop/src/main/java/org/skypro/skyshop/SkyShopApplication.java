package org.skypro.skyshop;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Включает @ComponentScan (находит @Service, @RestController)
public class SkyShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkyShopApplication.class, args);  // Запуск сервера
    }
}