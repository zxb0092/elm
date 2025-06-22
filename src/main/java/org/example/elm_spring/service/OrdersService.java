package org.example.elm_spring.service;

import org.example.elm_spring.pojo.Business;
import org.example.elm_spring.pojo.OrderDetailet;
import org.example.elm_spring.pojo.Orders;

import java.util.List;

public interface OrdersService {
    Integer addOrders(Orders orders);

    List<OrderDetailet> getOrderDetailets(Integer orderId);

    Business getBusinessByOrderId(Integer orderId);

    Orders getOrderByOrderId(Integer orderId);

    void updateOrders(Orders orders);

    void deleteOrders(Integer orderId);
}
