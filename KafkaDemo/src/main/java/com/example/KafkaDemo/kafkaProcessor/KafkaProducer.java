package com.example.KafkaDemo.kafkaProcessor;


import com.example.KafkaDemo.model.CreateEmployeeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;
    private static final String AppUpdateTopic = "app_updates";
    private static final String EmployeeUpdatesTopic = "employee_updates";
    private static final String EmployeeDLQTopic = "employee_dlq";

    public void produceOnTopicAppUpdates(CreateEmployeeResponse message) {
        kafkaTemplate.send(AppUpdateTopic, message);
        log.info("message Produced on App Update Topic : " + message);
    }

    public void produceOnTopicEmployeeUpdates(CreateEmployeeResponse message) {
        kafkaTemplate.send(EmployeeUpdatesTopic, message);
        log.info("message Produced on App Update Topic : " + message);
    }

    public void produceOnTopicEmployeeDLQ(CreateEmployeeResponse message) {
        kafkaTemplate.send(EmployeeDLQTopic, message);
        log.info("message Produced on App Update Topic : " + message);
    }
}
