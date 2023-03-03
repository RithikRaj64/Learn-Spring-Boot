package com.rithik.learn.services;

import java.util.List;

import com.rithik.learn.repositories.StudentRepository;
import com.rithik.learn.models.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager entityManager;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByField(String field) {
        return studentRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public List<Student> getStudentsByMarks(int marks) {
        return studentRepository.findByMarksGreaterThan(marks);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public String deleteStudent(String name) {
        try {
            studentRepository.deleteByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return name + " not found";
        }
        return name + " deleted";
    }

    public String updateStudent(String name, int marks) {
        try {
            studentRepository.updateMarksByName(name, marks);
        } catch (Exception e) {
            e.printStackTrace();
            return name + " not found";
        }
        return name + " updated";
    }

    public List<Student> getStudentsByNameStartingWith(String name) {
        return studentRepository.findByNameStartingWith(name);
    }

    public List<Student> getStudentsByNameEndingWith(String name) {
        return studentRepository.findByNameEndingWith(name);
    }

    public List<Student> getStudentsByNameContaining(String name) {
        return studentRepository.findByNameContaining(name);
    }

    // Entity Manager
    public Student getStudentByIdUsingEntityManager(int id) {
        Query q = this.entityManager.createQuery("SELECT s FROM Student s WHERE s.id = :id");
        q.setParameter("id", id);
        return (Student) q.getSingleResult();
    }

    public String updateStudentUsingEntityManager(int id, int marks) {
        try {
            Query q = this.entityManager.createQuery("UPDATE Student s SET s.marks = :marks WHERE s.id = :id");
            q.setParameter("id", id);
            q.setParameter("marks", marks);
            q.executeUpdate();
        } catch (Exception e) {
            return "Student " + id + " not Found";
        }

        return "Student " + id + " updated";
    }

    // One to One Mapping
    // public String getStudentPlaceById(int id) {
    // try {
    // Query q = this.entityManager
    // .createQuery("SELECT a.place FROM Student s JOIN Address a ON s.id = a.id
    // WHERE s.id = :id");
    // q.setParameter("id", id);
    // return (String) q.getSingleResult();
    // } catch (Exception e) {
    // return "Student " + id + " not Found";
    // }
    // }

    // public List<Address> getAddressesById(int id) {
    // Query q = this.entityManager.createQuery("SELECT a FROM Address a WHERE
    // fk_stu_id = :id");
    // q.setParameter("id", id);
    // return (List<Address>) q.getResultList();
    // }
}
