package org.example.elm_spring.service;

import org.example.elm_spring.pojo.Business;
import org.example.elm_spring.pojo.OrderDetailet;
import org.example.elm_spring.pojo.Orders;

import java.util.List;

/**
 * 订单业务逻辑接口。
 * 定义订单的创建、查询、更新和删除操作。
 */
public interface OrdersService {

    /**
     * 创建订单。
     * 若该用户在该商家已存在未支付订单，则返回 -1；否则生成订单记录、订单明细并清空购物车。
     *
     * @param orders 订单信息对象（包含 userId、businessId、daId、orderTotal）
     * @return 成功时返回订单 ID，失败时返回 -1
     */
    Integer addOrders(Orders orders);

    /**
     * 查询指定订单的所有明细，并填充每条明细对应的食品信息。
     *
     * @param orderId 订单 ID
     * @return 携带食品信息的订单明细列表
     */
    List<OrderDetailet> getOrderDetailets(Integer orderId);

    /**
     * 按订单 ID 查询该订单所属的商家信息。
     *
     * @param orderId 订单 ID
     * @return 对应的商家对象
     */
    Business getBusinessByOrderId(Integer orderId);

    /**
     * 按订单 ID 查询订单信息，并填充收货地址信息。
     *
     * @param orderId 订单 ID
     * @return 携带收货地址的订单对象
     */
    Orders getOrderByOrderId(Integer orderId);

    /**
     * 更新订单状态（如从未支付改为已支付）。
     *
     * @param orders 包含 orderId 和最新 orderState 的订单对象
     */
    void updateOrders(Orders orders);

    /**
     * 删除订单及其所有明细记录。
     *
     * @param orderId 待删除的订单 ID
     */
    void deleteOrders(Integer orderId);
}
