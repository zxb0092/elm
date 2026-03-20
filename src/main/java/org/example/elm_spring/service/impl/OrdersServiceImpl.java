package org.example.elm_spring.service.impl;

import org.example.elm_spring.mapper.CartMapper;
import org.example.elm_spring.mapper.OrdersMapper;
import org.example.elm_spring.pojo.*;
import org.example.elm_spring.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 订单业务逻辑实现类，实现 {@link OrdersService} 接口。
 * 通过 {@link OrdersMapper} 和 {@link CartMapper} 执行数据库操作，
 * 完成订单的创建、查询、更新和删除等业务逻辑。
 */
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private CartMapper cartMapper;

    /**
     * 创建订单。
     * 先检查该用户在该商家是否已存在未支付订单：
     * <ul>
     *   <li>若已存在（count != 0），返回 -1 表示不允许重复下单；</li>
     *   <li>若不存在，生成随机 orderId、记录当前时间、将 orderState 置为 0（未支付），
     *       插入订单记录；然后根据购物车中数量不为 0 的条目逐一生成订单明细记录，
     *       并将该订单已处理的购物车条目数量置为 0（通过 orders 层），
     *       最后通过 CartMapper 删除该用户的所有购物车记录，彻底清空购物车。</li>
     * </ul>
     *
     * @param orders 订单信息对象（包含 userId、businessId、daId、orderTotal）
     * @return 成功时返回订单 ID，失败时返回 -1
     */
    @Override
    public Integer addOrders(Orders orders) {
        Integer count = ordersMapper.check(orders);
        if (count == 0){
            LocalDateTime dateTime = LocalDateTime.now();
            String date=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateTime);
            orders.setOrderId(new Random().nextInt(1000000));
            orders.setOrderDate(date);
            orders.setOrderState(0);
            ordersMapper.addOrders(orders);
            List<Cart> cartList = ordersMapper.getCarts(orders.getBusinessId(),orders.getUserId());
            for(Cart cart :cartList){
                OrderDetailet orderDetailet = new OrderDetailet();
                orderDetailet.setOrderId(orders.getOrderId());
                orderDetailet.setQuantity(cart.getQuantity());
                orderDetailet.setFoodId(cart.getFoodId());
                ordersMapper.addOrderDetailet(orderDetailet);
                ordersMapper.updateCartQuantity(cart.getCartId());
            }
            cartMapper.deleteAllCart(orders.getUserId());
            return orders.getOrderId();
        } else {
            return -1;
        }
    }

    /**
     * 查询指定订单的所有明细，并为每条明细填充对应的食品详情（价格、图片、名称、描述）。
     *
     * @param orderId 订单 ID
     * @return 携带食品信息的订单明细列表
     */
    @Override
    public List<OrderDetailet> getOrderDetailets(Integer orderId) {
        List<OrderDetailet> orderDetailetList = ordersMapper.getOrderDetailets(orderId);
        for(OrderDetailet orderDetailet : orderDetailetList){
            Food newFood = new Food();
            newFood.setFoodPrice(ordersMapper.getFoodByFoodId(orderDetailet.getFoodId()).getFoodPrice());
            newFood.setFoodImg(ordersMapper.getFoodByFoodId(orderDetailet.getFoodId()).getFoodImg());
            newFood.setFoodImg(ordersMapper.getFoodByFoodId(orderDetailet.getFoodId()).getFoodImg());
            newFood.setFoodName(ordersMapper.getFoodByFoodId(orderDetailet.getFoodId()).getFoodName());
            newFood.setFoodExplain(ordersMapper.getFoodByFoodId(orderDetailet.getFoodId()).getFoodExplain());
            orderDetailet.setFood(newFood);
        }
        return orderDetailetList;
    }

    /**
     * 按订单 ID 查询该订单所属的商家信息。
     * 先从订单中取出 businessId，再按 businessId 查询商家详情。
     *
     * @param orderId 订单 ID
     * @return 对应的商家对象
     */
    @Override
    public Business getBusinessByOrderId(Integer orderId) {
        Business business = ordersMapper.getBusiness(ordersMapper.getBusinessId(orderId));
        return business;
    }

    /**
     * 按订单 ID 查询订单信息，并填充关联的收货地址信息。
     *
     * @param orderId 订单 ID
     * @return 携带收货地址的订单对象
     */
    @Override
    public Orders getOrderByOrderId(Integer orderId) {
        Orders orders = ordersMapper.getOrderByOrderId(orderId);
        orders.setDeliveryAddress(ordersMapper.getAddressByDaId(orders.getDaId()));
        return orders;
    }

    /**
     * 更新订单状态（如将未支付状态改为已支付）。
     *
     * @param orders 包含 orderId 和最新 orderState 的订单对象
     */
    @Override
    public void updateOrders(Orders orders) {
        ordersMapper.updateOrders(orders);
    }

    /**
     * 删除订单及其所有明细记录。
     * 先删除 orders 表中的订单，再删除 orderdetailet 表中对应的所有明细。
     *
     * @param orderId 待删除的订单 ID
     */
    @Override
    public void deleteOrders(Integer orderId) {
        ordersMapper.deleteOrders(orderId);
        ordersMapper.deleteOrderDetails(orderId);
    }
}
