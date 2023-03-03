package com.rithik.learn.repositories;

import java.util.List;

import com.rithik.learn.models.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Positional Param
    // @Query(value = "SELECT * FROM students s WHERE s.marks > ?1", nativeQuery =
    // true)
    // List<Student> findByMarksGreaterThan(int marks);

    // Named Param
    // @Query(value = "SELECT * FROM students s WHERE s.marks > :mark", nativeQuery
    // = true)
    // List<Student> findByMarksGreaterThan(@Param("mark") int marks);

    // JPQL
    @Query("SELECT s FROM Student s WHERE s.marks > :mark")
    List<Student> findByMarksGreaterThan(@Param("mark") int marks);

    // Delete Query
    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.name = :name")
    void deleteByName(@Param("name") String name);

    // Update Query
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.marks = :marks WHERE s.name = :name")
    void updateMarksByName(@Param("name") String name, @Param("marks") int marks);

    // Start with query
    @Query("SELECT s FROM Student s WHERE s.name LIKE :name%")
    List<Student> findByNameStartingWith(String name);

    // End with query
    @Query("SELECT s FROM Student s WHERE s.name LIKE %:name")
    List<Student> findByNameEndingWith(String name);

    // Contains query
    @Query("SELECT s FROM Student s WHERE s.name LIKE %:name%")
    List<Student> findByNameContaining(String name);
}
