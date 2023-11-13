package com.example.useraddressservice.repository;

import com.example.useraddressservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {

    Optional<List<Address>> findByUserId(Long userId);
}
