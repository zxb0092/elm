package org.example.elm_spring.service;

import org.example.elm_spring.pojo.Business;

import java.util.List;

public interface BusinessService {
    List<Business> list();
    List<Business> search(String name);
    List<Business> getByOrderTypeId(Integer orderTypeId);
    void add(Business business);
    void delete(Integer businessId);

    Business getByBusinessId(Integer businessId);

    Double total(Integer businessId,Long userId);
    Integer getQuantity(Integer businessId,Long userId);

}
