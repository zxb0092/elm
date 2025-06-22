package org.example.elm_spring.service.impl;

import org.example.elm_spring.mapper.DeliveryAddressMapper;
import org.example.elm_spring.pojo.DeliveryAddress;
import org.example.elm_spring.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressImpl implements DeliveryAddressService {
    @Autowired
    private DeliveryAddressMapper deliveryAddressMapper;

    @Override
    public List<DeliveryAddress> list(Long userId) {
        return deliveryAddressMapper.list(userId);
    }

    @Override
    public void addAddress(DeliveryAddress deliveryAddress) {
        deliveryAddressMapper.addAddress(deliveryAddress);
    }

    @Override
    public void deleteAddress(Integer daId) {
        deliveryAddressMapper.deleteAddress(daId);
    }

    @Override
    public void updateAddress(DeliveryAddress deliveryAddress) {
        deliveryAddressMapper.updateAddress(deliveryAddress);
    }

    @Override
    public DeliveryAddress getByDaId(Integer daId) {
        return deliveryAddressMapper.getByDaId(daId);
    }

}
