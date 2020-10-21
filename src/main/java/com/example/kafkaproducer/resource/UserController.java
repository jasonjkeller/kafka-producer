package com.example.kafkaproducer.resource;

import com.example.kafkaproducer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserController {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "users";

    @GetMapping("/publish/{name}/{dept}/{salary}")
    public String post(@PathVariable("name") final String name, @PathVariable("dept") final String dept, @PathVariable("salary") final Long salary) {
        User user = new User(name, dept, salary);
        kafkaTemplate.send(TOPIC, user);

        return "Successfully published to topic: " + TOPIC + ", " + user;
    }
}
