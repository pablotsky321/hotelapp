package com.service.microservice_user;

import com.service.microservice_user.entities.TypeDocEntity;
import com.service.microservice_user.repositories.TypeDocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceUserApplication {

	@Autowired
	TypeDocRepository typeDocRepository;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceUserApplication.class, args);
	}

	@Bean
	CommandLineRunner start(){
		return args -> {
			TypeDocEntity typeDocEntity = new TypeDocEntity();
			typeDocEntity.setTypeDoc("CC");
			typeDocEntity.setDescription("Cedula de ciudadania");
			typeDocRepository.save(typeDocEntity);
		};
	}

}
