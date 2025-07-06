package com.shopzone.app.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopzone.app.dto.AddressDto;
import com.shopzone.app.entity.Address;
import com.shopzone.app.entity.User;
import com.shopzone.app.repo.AddressRepository;
import com.shopzone.app.repo.UserRepo;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private UserRepo userRepo;

    public List<AddressDto> getUserAddresses(Long userId) {
        return addressRepo.findByUserId(userId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public AddressDto saveAddress(AddressDto dto) {
        User user = userRepo.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Address address = new Address();
        address.setAddressLine(dto.getAddressLine());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCountry(dto.getCountry());
        address.setPincode(dto.getPincode());
        address.setLatitude(dto.getLatitude());
        address.setLongitude(dto.getLongitude());
        address.setUser(user);

        return mapToDto(addressRepo.save(address));
    }

    public void deleteAddress(Long id) {
        addressRepo.deleteById(id);
    }

    private AddressDto mapToDto(Address a) {
        AddressDto dto = new AddressDto();
        dto.setId(a.getId());
        dto.setAddressLine(a.getAddressLine());
        dto.setCity(a.getCity());
        dto.setState(a.getState());
        dto.setCountry(a.getCountry());
        dto.setPincode(a.getPincode());
        dto.setLatitude(a.getLatitude());
        dto.setLongitude(a.getLongitude());
        dto.setUserId(a.getUser().getId());
        return dto;
    }
}

