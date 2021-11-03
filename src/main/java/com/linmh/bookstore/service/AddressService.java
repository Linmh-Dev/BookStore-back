package com.linmh.bookstore.service;

import com.linmh.bookstore.bean.Address;

public interface AddressService {
    int insertAddress(Address address);

    Address selectAddress();
}
