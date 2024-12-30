package com.docPractice.RestDemo.repo;

import com.docPractice.RestDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long>
{


}
