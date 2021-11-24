package com.example.KafkaDemo.service;

import com.example.KafkaDemo.CassendraService.EmployeeInsert;
import com.example.KafkaDemo.CassendraService.SkillInsert;
import com.example.KafkaDemo.kafkaProcessor.KafkaProducer;
import com.example.KafkaDemo.model.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class EmployeeService {
    @Autowired
    EmployeeInsert empDatabase;
    @Autowired
    SkillInsert skillDatabase;
    @Autowired
    KafkaProducer producer;

    public ResponseEntity<CreateEmployeeResponse> createEmployeeService(CreateEmployeeInput input) {
        try {
            log.info("call for employee creation ");
            Optional<Employee> employeeOptional = empDatabase.findById(input.getEmp_id());
            log.info("fetched by id : " + employeeOptional);
            if (employeeOptional.isPresent()) {
                log.warn("data already present");
                producer.produceOnTopicAppUpdates(new CreateEmployeeResponse
                        (input.getEmp_id(), input.getEmp_name(), input.getEmp_city(), input.getEmp_phone(),
                                "Already Exists", input.getJava_exp(), input.getSpring_exp()));
                return new ResponseEntity<CreateEmployeeResponse>(new CreateEmployeeResponse
                        (input.getEmp_id(), input.getEmp_name(), input.getEmp_city(), input.getEmp_phone(),
                                "Already Exists", input.getJava_exp(), input.getSpring_exp()), HttpStatus.OK);
            } else {
                log.info("inserting into database");
                Employee empDetails = new Employee(input.getEmp_id(), input.getEmp_name(), input.getEmp_city(), input.getEmp_phone());
                log.info("empDetails : " + empDetails.toString());
                Skill skills = new Skill(input.getEmp_id(), input.getJava_exp(), input.getSpring_exp());
                empDatabase.save(empDetails);
                skillDatabase.save(skills);
                producer.produceOnTopicAppUpdates(new CreateEmployeeResponse(
                        input.getEmp_id(), input.getEmp_name(), input.getEmp_city(), input.getEmp_phone(),
                        "Created", input.getJava_exp(), input.getSpring_exp()));
                return new ResponseEntity<CreateEmployeeResponse>(new CreateEmployeeResponse(
                        input.getEmp_id(), input.getEmp_name(), input.getEmp_city(), input.getEmp_phone(),
                        "Created", input.getJava_exp(), input.getSpring_exp()), HttpStatus.OK);

            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public  List<CreateEmployeeInput> getEmpBySkill(FindSkillInput skillInput) {
        try{
            List<Skill> skills;
            if(skillInput.getSpring_exp()== null) {
                skills = skillDatabase.findByJavaSkill(skillInput.getJava_exp());
            }else {
                skills = skillDatabase.findBySpringSkill(skillInput.getSpring_exp());
            }
            List<Integer> ids = new ArrayList<>();
            for(Skill s:skills){
                ids.add(s.getEmp_id());
            }
            List<Employee> employees = empDatabase.findAllById(ids);
            return this.getSkillResponse(employees,skills);

        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private  List<CreateEmployeeInput> getSkillResponse(List<Employee> employees, List<Skill> skills) {
        List<CreateEmployeeInput> responseList = new ArrayList<>();
        for (int i=0;i<employees.size();i++){
            CreateEmployeeInput input = new CreateEmployeeInput(employees.get(i).getEmp_id(),employees.get(i).getEmp_city(),
                    employees.get(i).getEmp_name(),employees.get(i).getEmp_phone(),
                    skills.get(i).getJava_exp(),skills.get(i).getSpring_exp());
            responseList.add(input);
        }
        return responseList;
    }

}
