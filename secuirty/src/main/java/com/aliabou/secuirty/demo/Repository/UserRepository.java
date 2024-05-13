package com.aliabou.secuirty.demo.Repository;

import com.aliabou.secuirty.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query("SELECT e.id , e.name FROM User e")
    List<Object[]> findIdAndName();

    @Query("SELECT e.id, e.name FROM User e WHERE e.id = :id")
    Object[] findIdAndNameById(Long id);


}
