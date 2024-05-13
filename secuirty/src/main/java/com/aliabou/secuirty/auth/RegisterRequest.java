package com.aliabou.secuirty.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private double salary;
    private String email;
    private String password;
    private Long departmentId;
    private Long projectId;

}
