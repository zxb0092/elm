package org.example.elm_spring.service.impl;

import org.example.elm_spring.mapper.UserMapper;
import org.example.elm_spring.pojo.*;
import org.example.elm_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserInfo(Long userId) {
        return userMapper.getUserInfo(userId);
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public User register(User user) {
        User newUser = userMapper.getUserByUserName(user.getUserName());
        if (newUser == null){
            user.setDelTag(1);
            user.setUserId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
            userMapper.register(user);
            return user;
        }else {
            return null;
        }
    }

    @Override
    public List<Orders> getOrdersNotPay(Long userId) {
        List<Orders> ordersList = userMapper.getOrdersNotPay(userId);
        for(Orders orders : ordersList){
            Business newBusiness = userMapper.getBusiness(orders.getBusinessId());
            orders.setBusiness(newBusiness);
            List<OrderDetailet> orderDetailetList = userMapper.getOrderDetailet(orders.getOrderId());
            for(OrderDetailet orderDetailet : orderDetailetList){
                Food newFood = userMapper.getFood(orderDetailet.getFoodId());
                orderDetailet.setFood(newFood);
            }
            orders.setOrderDetailetList(orderDetailetList);
        }
        return ordersList;
    }

    @Override
    public List<Orders> getOrdersPay(Long userId) {
        List<Orders> ordersList = userMapper.getOrdersPay(userId);
        for(Orders orders : ordersList){
            Business newBusiness = userMapper.getBusiness(orders.getBusinessId());
            orders.setBusiness(newBusiness);
            List<OrderDetailet> orderDetailetList = userMapper.getOrderDetailet(orders.getOrderId());
            for(OrderDetailet orderDetailet : orderDetailetList){
                Food newFood = userMapper.getFood(orderDetailet.getFoodId());
                orderDetailet.setFood(newFood);
            }
            orders.setOrderDetailetList(orderDetailetList);
        }
        return ordersList;
    }
}
