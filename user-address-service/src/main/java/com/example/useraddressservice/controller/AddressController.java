package com.example.useraddressservice.controller;

import com.example.useraddressservice.entity.Address;
import com.example.useraddressservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    public Optional<List<Address>> getAddressByUserId(@PathVariable Long userId){
        return addressService.getAddressByUserId(userId);
    }
}
