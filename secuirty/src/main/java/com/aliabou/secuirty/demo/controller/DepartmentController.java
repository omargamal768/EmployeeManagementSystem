package com.aliabou.secuirty.demo.controller;



import com.aliabou.secuirty.demo.Services.DepartmentServices;
import com.aliabou.secuirty.entities.Department;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@SpringBootApplication
@RestController
@RequestMapping(path="api/dep")
public class DepartmentController {

    protected final DepartmentServices departmentServices;

    public DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }


    @GetMapping("all")
	public List<Department> getDepartments() {
		return departmentServices.getDepartments();}


    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable String id) {
        return departmentServices.getDepartmentById(Long.valueOf(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable Long id) {
        departmentServices.deleteDepartmentById(id);
        return new ResponseEntity<>("Department with ID " + id + " deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentServices.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department  department) {
        Department updatedDepartment = departmentServices.updateDepartment(department);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

}
