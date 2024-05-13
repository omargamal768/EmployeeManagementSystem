package com.aliabou.secuirty.demo.Services;


import com.aliabou.secuirty.demo.Repository.DepartmentRepo;
import com.aliabou.secuirty.entities.Department;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServices {
    @Autowired
    DepartmentRepo departmentRepo;



    public List<Department> getDepartments() {
        return departmentRepo.findAll();
    }
    public Department getDepartmentById(Long id) {
        return departmentRepo.findById(id).orElse(null);

    }
    public void deleteDepartmentById(Long id) {
        departmentRepo.deleteById(id);
    }
    public Department saveDepartment(Department department) {
        // Save the employee using the repository
        return departmentRepo.save(department);
    }
    public Department updateDepartment(Department department) {
        // Check if the employee exists
        if (departmentRepo.existsById(department.getDepartmentId())) {
            // Update the employee using the repository
            return departmentRepo.save(department);
        } else {
            // Handle the case where the employee does not exist
            throw new EntityNotFoundException("Department with ID " + department.getDepartmentId() + " not found");
        }
    }
}
