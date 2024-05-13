package com.aliabou.secuirty.entities;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read");
    @Getter
    private final String permission;
}
