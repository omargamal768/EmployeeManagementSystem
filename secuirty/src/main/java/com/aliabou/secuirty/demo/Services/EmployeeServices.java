package com.aliabou.secuirty.demo.Services;

import com.aliabou.secuirty.auth.AuthenticationResponse;
import com.aliabou.secuirty.auth.RegisterRequest;
import com.aliabou.secuirty.config.JwtService;
import com.aliabou.secuirty.entities.Role;
import com.aliabou.secuirty.entities.User;
import com.aliabou.secuirty.demo.Repository.UserRepository;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServices {

@Autowired
    private final UserRepository employeeRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public List<User> getEmployees() {
        return employeeRepo.findAll();
    }

    public User getEmployeeById(Integer id) {
        return employeeRepo.findById(id).orElse(null);}

    public void deleteEmployeeById(Integer id) {
        employeeRepo.deleteById(id);
    }


        public AuthenticationResponse register(RegisterRequest request) {
            var user = User.builder()
                    .name(request.getName())
                    .salary(request.getSalary())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            employeeRepo.save(user);
            var jwtToken =  jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).build();
        }


    public User saveEmployee(User employee) {
        // Save the employee using the repository
        return employeeRepo.save(employee);
    }
   public   User updateEmployee(User employee) {
        // Check if the employee exist
        if (employeeRepo.existsById(employee.getId())) {
            // Update the employee using the repository
            return employeeRepo.save(employee);
        } else {
            // Handle the case where the employee does not exist
            throw new EntityNotFoundException("Employee with ID " + employee.getId()+ " not found");}}


    public List<Object[]> getIdAndNameOfEmployees() {
        return employeeRepo.findIdAndName();}


    public Object[] getIdAndNameById(Long id) {

        return employeeRepo.findIdAndNameById(id);
    }





}
