package com.example.KafkaDemo.kafkaProcessor;


import com.example.KafkaDemo.model.CreateEmployeeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {
    @Autowired
    KafkaProducer producer;
    @KafkaListener(topics = "app_updates", groupId = "group_json",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeAppUpdate(CreateEmployeeResponse message) {
        try {
            System.out.println("kafka message consumed by app Updates : " + message.toString());
            if(message.getStatus().equals("Created")){
                log.info("message is valid,producing on employee_updates");
                producer.produceOnTopicEmployeeUpdates(message);
            }else{
                log.info("message is Not Valid,producing on DLQ");
                producer.produceOnTopicEmployeeDLQ(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
