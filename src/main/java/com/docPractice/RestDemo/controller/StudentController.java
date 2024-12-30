package com.docPractice.RestDemo.controller;

import com.docPractice.RestDemo.entity.Student;
import com.docPractice.RestDemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/demo/save")
    public Student save(@RequestBody Student student)
    {
        return studentService.save(student);
    }

    @GetMapping("/demo/{Id}")
    public Student getById(@PathVariable Long Id)
    {
        return studentService.getById(Id);

    }

    @GetMapping("/demo/getAll")
    public List<Student>getAll()
    {
        return studentService.findAll();
    }
}
