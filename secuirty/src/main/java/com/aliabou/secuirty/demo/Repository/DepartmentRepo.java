package com.aliabou.secuirty.demo.Repository;


import com.aliabou.secuirty.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> { }




