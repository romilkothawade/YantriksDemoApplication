package com.example.KafkaDemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Table("emp_skill")
public class Skill {
    @PrimaryKeyColumn(name = "emp_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    int emp_id;
    @PrimaryKeyColumn(name = "java_exp", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    double java_exp;
    @PrimaryKeyColumn(name = "spring_exp", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    double spring_exp;
}
