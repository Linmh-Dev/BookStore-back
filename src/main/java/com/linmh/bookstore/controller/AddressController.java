package com.linmh.bookstore.controller;

import com.linmh.bookstore.bean.Address;
import com.linmh.bookstore.bean.Response;
import com.linmh.bookstore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("address")
    public Object insertAddress(@RequestBody Address address){
        int res = addressService.insertAddress(address);
        if (res<0){
            return Response.failure();
        }
        return Response.success("添加地址成功");
    }

    @GetMapping("address")
    public Object queryAddress(){
        Address address = addressService.selectAddress();
        if (address == null) {
            return Response.failure();
        }
        return Response.success(address);
    }

}
