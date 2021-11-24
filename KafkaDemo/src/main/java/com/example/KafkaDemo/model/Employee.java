package com.example.KafkaDemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Table("emp")
public class Employee {
    @PrimaryKey
    int emp_id;
    @Column
    String emp_name;
    @Column
    String emp_city;
    @Column
    String emp_phone;
}
