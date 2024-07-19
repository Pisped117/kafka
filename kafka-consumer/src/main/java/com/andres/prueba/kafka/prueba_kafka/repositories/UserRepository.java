package com.andres.prueba.kafka.prueba_kafka.repositories;

import com.andres.prueba.kafka.prueba_kafka.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
