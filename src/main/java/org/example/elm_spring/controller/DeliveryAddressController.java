package org.example.elm_spring.controller;

import org.example.elm_spring.pojo.DeliveryAddress;
import org.example.elm_spring.pojo.Result;
import org.example.elm_spring.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliveryAddressController {
    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @GetMapping("/addressList/{userId}")
    public Result list(@PathVariable Long userId){
        List<DeliveryAddress> deliveryAddressList = deliveryAddressService.list(userId);
        return Result.success(deliveryAddressList);
    }
    @PostMapping("/addressList")
    public Result addAddress(@RequestBody DeliveryAddress deliveryAddress){
        deliveryAddressService.addAddress(deliveryAddress);
        return Result.success();
    }
    @DeleteMapping("/addressList/{daId}")
    public Result deleteAddress(@PathVariable Integer daId){
        deliveryAddressService.deleteAddress(daId);
        return Result.success();
    }
    @PutMapping("/addressList")
    public Result updateAddress(@RequestBody DeliveryAddress deliveryAddress){
        deliveryAddressService.updateAddress(deliveryAddress);
        return Result.success();
    }
    @GetMapping("/addressList/daId/{daId}")
    public Result getByDaId(@PathVariable Integer daId){
        DeliveryAddress deliveryAddress = deliveryAddressService.getByDaId(daId);
        return Result.success(deliveryAddress);
    }
}
