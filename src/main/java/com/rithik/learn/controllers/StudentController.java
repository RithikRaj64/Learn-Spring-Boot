package com.rithik.learn.controllers;

import java.util.List;

import com.rithik.learn.models.Student;
import com.rithik.learn.services.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Operation(summary = "Gets all the students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All the students are displayed"),
            @ApiResponse(responseCode = "404", description = "Students details not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Operation(summary = "Saves a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid student input"),
            @ApiResponse(responseCode = "404", description = "Students details not found")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "text/string")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student saved successfully");
    }

    @Operation(summary = "Gets a student by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All the students are displayed"),
            @ApiResponse(responseCode = "404", description = "Students details not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json", value = "/id/{id}")
    public Student getStudentByIdUsingEntityManager(@PathVariable int id) {
        return studentService.getStudentByIdUsingEntityManager(id);
    }

    @Operation(summary = "Gets all the students sorted by field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All the students are displayed"),
            @ApiResponse(responseCode = "404", description = "Students details not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json", value = "/field/{field}")
    public List<Student> getStudentsByField(@PathVariable String field) {
        return studentService.getStudentsByField(field);
    }

    @Operation(summary = "Gets all the students with marks greater than")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All the students are displayed"),
            @ApiResponse(responseCode = "404", description = "Students details not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json", value = "/marks/{marks}")
    public List<Student> getStudentsByMarks(@PathVariable int marks) {
        return studentService.getStudentsByMarks(marks);
    }

    @Operation(summary = "Deletes a student by Name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(consumes = "application/json", produces = "text/string", value = "/{name}")
    public String deleteStudent(@PathVariable String name) {
        return studentService.deleteStudent(name);
    }

    @Operation(summary = "Updates a student's mark   by Name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid student input"),
            @ApiResponse(responseCode = "404", description = "Students details not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(consumes = "application/json", produces = "text/string", value = "/{name}/{marks}")
    public String updateStudent(@PathVariable String name, @PathVariable int marks) {
        return studentService.updateStudent(name, marks);
    }

    @Operation(summary = "Gets all the students whose name starts with")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All the students are displayed"),
            @ApiResponse(responseCode = "404", description = "Students details not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json", value = "start/{name}")
    public List<Student> getStudentsByNameStartingWith(@PathVariable String name) {
        return studentService.getStudentsByNameStartingWith(name);
    }

    @Operation(summary = "Gets all the students whose name ends with")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All the students are displayed"),
            @ApiResponse(responseCode = "404", description = "Students details not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json", value = "/end/{name}")
    public List<Student> getStudentsByNameEndingWith(@PathVariable String name) {
        return studentService.getStudentsByNameEndingWith(name);
    }

    @Operation(summary = "Gets all the students whose name contains")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All the students are displayed"),
            @ApiResponse(responseCode = "404", description = "Students details not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json", value = "/contains/{name}")
    public List<Student> getStudentsByNameContaining(@PathVariable String name) {
        return studentService.getStudentsByNameContaining(name);
    }

    // One to One Mapping
    // @GetMapping("/place/{id}")
    // public String getStudentPlaceById(@PathVariable int id) {
    // return studentService.getStudentPlaceById(id);
    // }

    // @GetMapping("/address/{id}")
    // public List<Address> getAddressesById(@PathVariable int id) {
    // return studentService.getAddressesById(id);
    // }
}