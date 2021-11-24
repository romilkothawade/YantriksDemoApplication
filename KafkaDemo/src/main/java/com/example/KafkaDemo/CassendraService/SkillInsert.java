package com.example.KafkaDemo.CassendraService;

import com.example.KafkaDemo.model.Skill;
//import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillInsert extends CassandraRepository<Skill, Integer> {

    @Query("select * from emp_skill where java_exp=?0 ALLOW FILTERING;")
    List<Skill> findByJavaSkill(double exp);

    @Query("select * from emp_skill where spring_exp=?0 ALLOW FILTERING;")
    List<Skill> findBySpringSkill(double exp);
}
