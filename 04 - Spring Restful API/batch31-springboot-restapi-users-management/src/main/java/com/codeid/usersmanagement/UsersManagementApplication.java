package com.codeid.usersmanagement;

import com.codeid.usersmanagement.properties.FileStorageProperties;
import com.codeid.usersmanagement.service.FileStorageService;
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
public class UsersManagementApplication implements CommandLineRunner {

	@Resource
	private FileStorageService fileStorageService;

	public static void main(String[] args) {
		SpringApplication.run(UsersManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileStorageService.deleteAll();
		fileStorageService.init();
	}
}
