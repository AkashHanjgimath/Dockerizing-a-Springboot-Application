package com.docPractice.RestDemo.service;

import com.docPractice.RestDemo.entity.Student;
import com.docPractice.RestDemo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Student save(Student student)
    {
        return studentRepo.save(student);
    }

    public Student getById(Long id)
    {
        return studentRepo.findById(id).get();

    }

    public List<Student>findAll()
    {
        return studentRepo.findAll();
    }
}
