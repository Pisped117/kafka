package com.andres.prueba.kafka.prueba_kafka.service.producer;

import com.andres.prueba.kafka.prueba_kafka.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ServiceProducer {
    private static final String TOPIC = "topic_one";

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void sendMessage(User user){


        kafkaTemplate.send(TOPIC, user);
        /*User userTmp = new User();
        userTmp.setName(user.getName());
        userTmp.setLastname(user.getLastname());

       for (int i = 0; i < 30000; i++){
            String userName = userTmp.getName() +'-'+ i;
            String userLastname = userTmp.getLastname() +'-'+ i;
            user.setName(userName);
            user.setLastname(userLastname);
            kafkaTemplate.send(TOPIC, user);
        }*/
    }


}
