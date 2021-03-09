package com.st121334.midterm2021.service;

import com.st121334.midterm2021.model.Address;
import com.st121334.midterm2021.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepo addressRepo;

    @Override
    public Address save(Address address) {
        return addressRepo.save(address);
    }
}
