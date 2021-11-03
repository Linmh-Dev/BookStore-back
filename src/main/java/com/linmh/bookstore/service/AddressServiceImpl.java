package com.linmh.bookstore.service;

import com.linmh.bookstore.bean.Address;
import com.linmh.bookstore.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressMapper addressMapper;
    @Override
    public int insertAddress(Address address) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name == null || name.isEmpty()) {
            return -1;
        }
        address.setUsername(name);
        addressMapper.deleteByUsername(name);
        return addressMapper.insert(address);
    }

    @Override
    public Address selectAddress() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name == null) {
            return null;
        }
        Address address = addressMapper.selectAddressByUsername(name);

        return address;
    }

}
