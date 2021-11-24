package com.example.KafkaDemo.controller;

import com.example.KafkaDemo.model.CreateEmployeeInput;
import com.example.KafkaDemo.model.CreateEmployeeResponse;
import com.example.KafkaDemo.model.FindSkillInput;
import com.example.KafkaDemo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public class demoController {
    @Autowired
    EmployeeService service;

    @PostMapping("/createEmployee")
    public ResponseEntity<CreateEmployeeResponse> createEmployee(@RequestBody CreateEmployeeInput input) {
        try {
            log.info("call for employee creation ");
            System.out.println("input is : " + input);
            return service.createEmployeeService(input);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/findEmpSkillset")
    public ResponseEntity<List<CreateEmployeeInput>> getEmpBySkillSet(@RequestBody FindSkillInput input) {
        try {
            log.info("call for employee fetching by skill set ");
            List<CreateEmployeeInput> response = service.getEmpBySkill(input);
            return new ResponseEntity<List<CreateEmployeeInput>>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
