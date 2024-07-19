package com.andres.prueba.kafka.prueba_kafka;

import com.andres.prueba.kafka.prueba_kafka.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaConsumerApplication implements CommandLineRunner {

	@Autowired
	private DatabaseConfig databaseConfig;

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (args.length > 0){
			for (String arg: args){
				if (arg.startsWith("--DB_URL=")){
					String urlDb = arg.substring(9);
					System.out.println(urlDb);
					databaseConfig.setUrl(urlDb);
				} else if (arg.startsWith("--DB_USERNAME=")) {
					String usernameDb = arg.substring(14);
					System.out.println(usernameDb);
					databaseConfig.setUsername(usernameDb);
				} else if (arg.startsWith("--DB_PASSWORD=")) {
					String passwordDb = arg.substring(14);
					System.out.println(passwordDb);
					databaseConfig.setPassword(passwordDb);
				}
			}
		}

		System.out.println("URL DE BASE DE DATOS: " + databaseConfig.getUrl());
		System.out.println("USERNAME DE BASE DE DATOS: " + databaseConfig.getUsername());
		System.out.println("PASSWORD DE BASE DE DATOS: " + databaseConfig.getPassword());
	}
}
