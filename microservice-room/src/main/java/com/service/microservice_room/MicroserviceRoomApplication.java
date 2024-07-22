package com.service.microservice_room;

import com.service.microservice_room.entities.StateEntity;
import com.service.microservice_room.entities.TypeRoomEntity;
import com.service.microservice_room.repositories.StateRepository;
import com.service.microservice_room.repositories.TypeRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceRoomApplication {

	@Autowired
	TypeRoomRepository typeRoomRepository;

	@Autowired
	StateRepository stateRepository;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRoomApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(){
		return args -> {

			StateEntity state1 = new StateEntity();
			StateEntity state2 = new StateEntity();
			StateEntity state3 = new StateEntity();
			state1.setState("MAINTENANCE");
			state2.setState("FREE");
			state3.setState("TAKEN");
			stateRepository.saveAll(List.of(state1,state2,state3));

			TypeRoomEntity type1 = new TypeRoomEntity();
			TypeRoomEntity type2 = new TypeRoomEntity();
			TypeRoomEntity type3 = new TypeRoomEntity();
			TypeRoomEntity type4 = new TypeRoomEntity();
			TypeRoomEntity type5 = new TypeRoomEntity();
			type1.setRoomType("Suite");
			type1.setDescription("double room with a bathroom");
			type2.setRoomType("Junior Suite");
			type2.setDescription("double room with a bathroom and a living room");
			type3.setRoomType("Big Suite");
			type3.setDescription("two or more double rooms their corresponding and a common living room");
			type4.setRoomType("Family");
			type4.setDescription("room with a double bed and one or more individual beds for children");
			type5.setRoomType("Matrimonial");
			type5.setDescription("room with a double bed");

			typeRoomRepository.saveAll(List.of(type1,type2,type3,type4,type5));

		};
	}

}
