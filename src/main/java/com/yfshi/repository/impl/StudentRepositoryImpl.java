package com.yfshi.repository.impl;

import com.yfshi.entity.Student;
import com.yfshi.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private static Map<Long,Student> studentMap= new HashMap<Long, Student>();



    public Collection<Student> findAll(){
        return studentMap.values();
    }
    public Student findById(long id){
        return studentMap.get(id);
    }
    public void saveOrUpdate(Student student){
        studentMap.put(student.getId(),student);    //存储过更新
    }
    public void deleteById(long id){
        studentMap.remove(id);
    }

}
