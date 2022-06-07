package com.example.cinema.repository;

import com.example.cinema.model.ERole;
import com.example.cinema.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
