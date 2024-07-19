package com.andres.prueba.kafka.prueba_kafka.controller;

import com.andres.prueba.kafka.prueba_kafka.models.User;
import com.andres.prueba.kafka.prueba_kafka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kafka/consumer")
public class ControllerConsumer {

    @Autowired
    private UserService consumerService;

    @PostMapping("/consume")
    public void consumeMessage(@RequestBody String user){
        consumerService.consume(user);
    }

    @GetMapping
    public List<User> findUsers(){
        return consumerService.findUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        return consumerService.findUserById(id).orElseThrow();
    }

}
