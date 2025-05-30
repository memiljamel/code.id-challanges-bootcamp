package com.codeid.eshopay;

import com.codeid.eshopay.properties.FileStorageProperties;
import com.codeid.eshopay.service.FileStorageService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class EshopayApplication implements CommandLineRunner {

	@Resource
	private FileStorageService fileStorageService;

	public static void main(String[] args) {
		SpringApplication.run(EshopayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileStorageService.deleteAll();
		fileStorageService.init();
	}
}
