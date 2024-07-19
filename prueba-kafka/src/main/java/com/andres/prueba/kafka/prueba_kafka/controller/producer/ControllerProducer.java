package com.andres.prueba.kafka.prueba_kafka.controller.producer;

import com.andres.prueba.kafka.prueba_kafka.models.User;
import com.andres.prueba.kafka.prueba_kafka.service.producer.ServiceProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class ControllerProducer {

    @Autowired
    private ServiceProducer producer;

    @Autowired
    private User user;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody User user){
        producer.sendMessage(user);
        return ResponseEntity.ok("Message sent successfully");
    }

}
