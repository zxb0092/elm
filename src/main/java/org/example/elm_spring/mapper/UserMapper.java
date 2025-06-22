package org.example.elm_spring.mapper;

import org.example.elm_spring.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where userId = #{userId}")
    User getUserInfo(Long userId);
    @Select("select * from user where userName=#{userName} and password=#{password}")
    User login(User user);

    @Insert("insert into user(userId, password, userName, userSex, userImg, delTag)" +
            "values (#{userId},#{password}, #{userName}, #{userSex}, #{userImg}, #{delTag})")
    void register(User user);

    @Select("select * from orders where userId=#{userId} and orderState = 0")
    List<Orders> getOrdersNotPay(Long userId);

    @Select("select * from orders where userId=#{userId} and orderState = 1")
    List<Orders> getOrdersPay(Long userId);
    @Select("select * from business where businessId = #{businessId}")
    Business getBusiness(Integer businessId);

    @Select("select * from orderdetailet where orderId = #{orderId}")
    List<OrderDetailet> getOrderDetailet(Integer orderId);

    @Select("select * from food where foodId = #{foodId}")
    Food getFood(Integer foodId);

    @Select("select * from user where userName = #{userName}")
    User getUserByUserName(String userName);
}
