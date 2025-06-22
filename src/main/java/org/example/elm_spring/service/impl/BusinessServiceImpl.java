package org.example.elm_spring.service.impl;

import org.example.elm_spring.mapper.BusinessMapper;
import org.example.elm_spring.pojo.Business;
import org.example.elm_spring.pojo.Cart;
import org.example.elm_spring.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Business> list() {
        return businessMapper.list();
    }

    @Override
    public List<Business> search(String name){
        return businessMapper.search(name);
    }

    @Override
    public List<Business> getByOrderTypeId(Integer orderTypeId) {
        return businessMapper.getByOrderTypeId(orderTypeId);
    }

    @Override
    public void add(Business business) {
        businessMapper.add(business);
    }

    @Override
    public void delete(Integer businessId) {
        businessMapper.deleteBusiness(businessId);
        businessMapper.deleteFood(businessId);
    }

    @Override
    public Business getByBusinessId(Integer businessId) {
        return businessMapper.getByBusinessId(businessId);
    }

    @Override
    public Double total(Integer businessId, Long userId) {
        Double total = 0.0;
        List<Cart> cartList = businessMapper.getCartByBusiness(businessId,userId);
        for(Cart cart : cartList){
            Double price =  businessMapper.getPrice(cart.getFoodId());
            total+=price*cart.getQuantity();
        }
        Double deliveryPrice = businessMapper.getByBusinessId(businessId).getDeliveryPrice();
        total+=deliveryPrice;
        return total;
    }

    @Override
    public Integer getQuantity(Integer businessId, Long userId) {
        Integer quantity = 0;
        List<Cart> cartList = businessMapper.getCartByBusiness(businessId,userId);
        for(Cart cart : cartList){
            quantity+=cart.getQuantity();
        }
        return quantity;
    }
}

