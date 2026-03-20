package org.example.elm_spring.mapper;

import org.example.elm_spring.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户数据访问接口（MyBatis Mapper）。
 * 提供对 user、orders、orderdetailet、food、business 表的查询与操作方法，
 * 支持用户信息查询、登录验证、注册及订单查询等功能。
 */
@Mapper
public interface UserMapper {

    /**
     * 按用户 ID 查询用户详情。
     *
     * @param userId 用户 ID
     * @return 对应的用户对象，若不存在则返回 null
     */
    @Select("select * from user where userId = #{userId}")
    User getUserInfo(Long userId);

    /**
     * 根据用户名和密码验证登录。
     *
     * @param user 包含 userName 和 password 的用户对象
     * @return 登录成功返回用户对象，否则返回 null
     */
    @Select("select * from user where userName=#{userName} and password=#{password}")
    User login(User user);

    /**
     * 注册新用户，将用户信息插入 user 表。
     *
     * @param user 待注册的用户对象（需包含 userId、password、userName、userSex、userImg、delTag）
     */
    @Insert("insert into user(userId, password, userName, userSex, userImg, delTag)" +
            "values (#{userId},#{password}, #{userName}, #{userSex}, #{userImg}, #{delTag})")
    void register(User user);

    /**
     * 查询指定用户的所有未支付订单（orderState = 0）。
     *
     * @param userId 用户 ID
     * @return 未支付订单列表
     */
    @Select("select * from orders where userId=#{userId} and orderState = 0")
    List<Orders> getOrdersNotPay(Long userId);

    /**
     * 查询指定用户的所有已支付订单（orderState = 1）。
     *
     * @param userId 用户 ID
     * @return 已支付订单列表
     */
    @Select("select * from orders where userId=#{userId} and orderState = 1")
    List<Orders> getOrdersPay(Long userId);

    /**
     * 按商家 ID 查询商家信息。
     *
     * @param businessId 商家 ID
     * @return 对应的商家对象
     */
    @Select("select * from business where businessId = #{businessId}")
    Business getBusiness(Integer businessId);

    /**
     * 按订单 ID 查询该订单的所有明细。
     *
     * @param orderId 订单 ID
     * @return 订单明细列表
     */
    @Select("select * from orderdetailet where orderId = #{orderId}")
    List<OrderDetailet> getOrderDetailet(Integer orderId);

    /**
     * 按食品 ID 查询食品详情。
     *
     * @param foodId 食品 ID
     * @return 对应的食品对象
     */
    @Select("select * from food where foodId = #{foodId}")
    Food getFood(Integer foodId);

    /**
     * 按用户名查询用户（用于注册时检查用户名是否已存在）。
     *
     * @param userName 用户名
     * @return 若存在则返回用户对象，否则返回 null
     */
    @Select("select * from user where userName = #{userName}")
    User getUserByUserName(String userName);
}
