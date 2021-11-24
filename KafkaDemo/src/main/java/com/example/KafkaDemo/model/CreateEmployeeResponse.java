package com.example.KafkaDemo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j

public class CreateEmployeeResponse{
    int emp_id;
    String emp_name, emp_city, emp_phone,Status;
    Double java_exp,spring_exp;


}
