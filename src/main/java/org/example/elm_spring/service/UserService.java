package org.example.elm_spring.service;

import org.example.elm_spring.pojo.Orders;
import org.example.elm_spring.pojo.User;

import java.util.List;

public interface UserService {

    User getUserInfo(Long userId);
    User login(User user);

    User register(User user);

    List<Orders> getOrdersNotPay(Long userId);
    List<Orders> getOrdersPay(Long userId);
}
