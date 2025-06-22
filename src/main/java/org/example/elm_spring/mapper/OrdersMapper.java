package org.example.elm_spring.mapper;

import org.example.elm_spring.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrdersMapper {

    @Insert("INSERT INTO orders (orderId, userId, businessId, orderDate, orderState, daId, orderTotal) VALUES (#{orderId}, #{userId}, #{businessId}, #{orderDate}, #{orderState}, #{daId}, #{orderTotal})")
    void addOrders(Orders orders);

    @Select("select * from cart where businessId = #{businessId} and userId = #{userId} and quantity != 0")
    List<Cart> getCarts(Integer businessId,Long userId);

    @Insert("insert into  orderdetailet(orderId, foodId, quantity) " +
            "values (#{orderId},#{foodId},#{quantity})")
   void addOrderDetailet(OrderDetailet orderDetailet);

    @Update("update cart set quantity = 0 where cartId = #{cartId}")
    void updateCartQuantity(Integer cartId);

    @Select("select * from orderdetailet where orderId = #{orderId}")
    List<OrderDetailet> getOrderDetailets(Integer orderId);

    @Select("select count(*) from orders where userId = #{userId} and businessId = #{businessId} and orderState = 0")
    Integer check(Orders orders);

    @Select("select * from deliveryaddress where daId = #{daId}")
    DeliveryAddress getAddressByDaId(Integer daId);

    @Select("select * from food where foodId = #{foodId}")
    Food getFoodByFoodId(Integer foodId);

    @Select("select businessId from orders where orderId=#{orderId}")
    Integer getBusinessId(Integer orderId);

    @Select("select * from business where businessId = #{businessId}")
    Business getBusiness(Integer businessId);

    @Select("select * from orders where orderId=#{orderId}")
    Orders getOrderByOrderId(Integer orderId);

    @Update("update orders set orderState = #{orderState} where orderId = #{orderId}")
    void updateOrders(Orders orders);

    @Delete("delete from orders where orderId = #{orderId}")
    void deleteOrders(Integer orderId);

    @Delete("delete from orderdetailet where orderId = #{orderId}")
    void deleteOrderDetails(Integer orderId);
}
