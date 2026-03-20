package org.example.elm_spring.mapper;

import org.example.elm_spring.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 订单数据访问接口（MyBatis Mapper）。
 * 提供对 orders、orderdetailet、cart、food、business、deliveryaddress 表的操作方法，
 * 支持订单的创建、查询、更新和删除，以及关联数据的读取。
 */
@Mapper
public interface OrdersMapper {

    /**
     * 新增订单记录。
     *
     * @param orders 待新增的订单对象（包含 orderId、userId、businessId、orderDate、orderState、daId、orderTotal）
     */
    @Insert("INSERT INTO orders (orderId, userId, businessId, orderDate, orderState, daId, orderTotal) VALUES (#{orderId}, #{userId}, #{businessId}, #{orderDate}, #{orderState}, #{daId}, #{orderTotal})")
    void addOrders(Orders orders);

    /**
     * 查询指定商家下指定用户的购物车条目（仅包含数量不为 0 的记录），用于生成订单明细。
     *
     * @param businessId 商家 ID
     * @param userId     用户 ID
     * @return 购物车条目列表
     */
    @Select("select * from cart where businessId = #{businessId} and userId = #{userId} and quantity != 0")
    List<Cart> getCarts(Integer businessId, Long userId);

    /**
     * 新增订单明细记录。
     *
     * @param orderDetailet 待新增的订单明细对象（包含 orderId、foodId、quantity）
     */
    @Insert("insert into  orderdetailet(orderId, foodId, quantity) " +
            "values (#{orderId},#{foodId},#{quantity})")
    void addOrderDetailet(OrderDetailet orderDetailet);

    /**
     * 将指定购物车条目的数量置为 0（下单后清空）。
     *
     * @param cartId 购物车条目 ID
     */
    @Update("update cart set quantity = 0 where cartId = #{cartId}")
    void updateCartQuantity(Integer cartId);

    /**
     * 查询指定订单的所有订单明细。
     *
     * @param orderId 订单 ID
     * @return 订单明细列表
     */
    @Select("select * from orderdetailet where orderId = #{orderId}")
    List<OrderDetailet> getOrderDetailets(Integer orderId);

    /**
     * 检查指定用户在指定商家是否存在未支付订单。
     *
     * @param orders 包含 userId 和 businessId 的订单对象
     * @return 未支付订单的数量（0 表示不存在）
     */
    @Select("select count(*) from orders where userId = #{userId} and businessId = #{businessId} and orderState = 0")
    Integer check(Orders orders);

    /**
     * 按地址 ID 查询收货地址。
     *
     * @param daId 收货地址 ID
     * @return 对应的收货地址对象
     */
    @Select("select * from deliveryaddress where daId = #{daId}")
    DeliveryAddress getAddressByDaId(Integer daId);

    /**
     * 按食品 ID 查询食品详情。
     *
     * @param foodId 食品 ID
     * @return 对应的食品对象
     */
    @Select("select * from food where foodId = #{foodId}")
    Food getFoodByFoodId(Integer foodId);

    /**
     * 按订单 ID 查询该订单所属的商家 ID。
     *
     * @param orderId 订单 ID
     * @return 商家 ID
     */
    @Select("select businessId from orders where orderId=#{orderId}")
    Integer getBusinessId(Integer orderId);

    /**
     * 按商家 ID 查询商家信息。
     *
     * @param businessId 商家 ID
     * @return 对应的商家对象
     */
    @Select("select * from business where businessId = #{businessId}")
    Business getBusiness(Integer businessId);

    /**
     * 按订单 ID 查询订单信息。
     *
     * @param orderId 订单 ID
     * @return 对应的订单对象
     */
    @Select("select * from orders where orderId=#{orderId}")
    Orders getOrderByOrderId(Integer orderId);

    /**
     * 更新订单状态。
     *
     * @param orders 包含 orderId 和最新 orderState 的订单对象
     */
    @Update("update orders set orderState = #{orderState} where orderId = #{orderId}")
    void updateOrders(Orders orders);

    /**
     * 按订单 ID 删除订单记录。
     *
     * @param orderId 待删除的订单 ID
     */
    @Delete("delete from orders where orderId = #{orderId}")
    void deleteOrders(Integer orderId);

    /**
     * 按订单 ID 删除所有对应的订单明细记录。
     *
     * @param orderId 订单 ID
     */
    @Delete("delete from orderdetailet where orderId = #{orderId}")
    void deleteOrderDetails(Integer orderId);
}
