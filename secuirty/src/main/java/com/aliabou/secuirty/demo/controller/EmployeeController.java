package com.aliabou.secuirty.demo.controller;



import com.aliabou.secuirty.demo.Services.EmployeeServices;
import com.aliabou.secuirty.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@SpringBootApplication
@RestController
@RequestMapping(path="/api/employee")
public class EmployeeController {
@Autowired
    protected final EmployeeServices employeeServices;

    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }


    @GetMapping("/all")
	public List<User> getEmployees() {
		return employeeServices.getEmployees();}


    @GetMapping("/{id}")
    public User getEmployeeById(@PathVariable String id) {
        return employeeServices.getEmployeeById(Integer.valueOf(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        employeeServices.deleteEmployeeById(id);
        return new ResponseEntity<>("Employee with ID " + id + " deleted successfully", HttpStatus.OK);
    }

   @PostMapping("/register")
   public ResponseEntity<User> saveEmployee(@RequestBody User employee) {
       User savedEmployee = employeeServices.saveEmployee(employee);
       return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
   }
   @PutMapping("/update")
    public User updateEmployee(@RequestBody User employee) {
       return employeeServices.updateEmployee(employee);
    }
    @GetMapping("/idAndName")
    public List<Map<String, Object>> getIdAndNameOfEmployees() {
        List<Object[]> idAndNameList = employeeServices.getIdAndNameOfEmployees();

        // Convert the list of object arrays to a list of maps
        return idAndNameList.stream()
                .map(objects -> {
                    Map<String, Object> map = Map.of("id", objects[0], "name", objects[1]);
                    return map;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/idAndName")
    public Object[] getIdAndNameOfEmployeeById(@PathVariable Long id) {
        Object[] result = employeeServices.getIdAndNameById(id);
        return result;
    }





}
