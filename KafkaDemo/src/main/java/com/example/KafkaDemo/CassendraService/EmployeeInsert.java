package com.example.KafkaDemo.CassendraService;

import com.example.KafkaDemo.model.Employee;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeInsert extends CassandraRepository<Employee,Integer> {

}
