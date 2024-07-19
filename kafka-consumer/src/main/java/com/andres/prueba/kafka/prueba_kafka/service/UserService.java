package com.andres.prueba.kafka.prueba_kafka.service;

import com.andres.prueba.kafka.prueba_kafka.models.User;
import com.andres.prueba.kafka.prueba_kafka.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheManager = "cacheManager")
public class UserService {
    private static final String TOPIC = "topic_one";

    @Autowired
    private UserRepository repository;

    @KafkaListener(topics = TOPIC, groupId = "${spring.kafka.consumer-group-id}")
    public void consume(String messageUser){

        if (messageUser.isEmpty()){
            throw new RuntimeException("El mensaje no se puede procesar proque viene vacio!");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User user = objectMapper.readValue(messageUser, User.class);
            User userDb = repository.save(user);
            if (userDb != null){
                System.out.println("El usuario \n" + userDb + "\nfue guardado con exito en la base de datos!");
            }

        }catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @Cacheable(value = "findUsers", cacheManager = "cacheManager")
    public List<User> findUsers() {
        System.out.println("fetch in database...");
        return (List<User>) repository.findAll();

    }

    @Cacheable(value = "findUserId", cacheManager = "cacheManager")
    public Optional<User> findUserById (Long user_id) {
        System.out.println("fetch in database by id...");
        Optional<User> user = repository.findById(user_id);
        return user;

    }

}
