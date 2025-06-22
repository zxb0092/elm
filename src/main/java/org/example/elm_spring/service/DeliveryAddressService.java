package org.example.elm_spring.service;

import org.example.elm_spring.pojo.DeliveryAddress;

import java.util.List;

public interface DeliveryAddressService {
    List<DeliveryAddress> list(Long userId);

    void addAddress(DeliveryAddress deliveryAddress);

    void deleteAddress(Integer daId);
    void updateAddress(DeliveryAddress deliveryAddress);

    DeliveryAddress getByDaId(Integer daId);

}
