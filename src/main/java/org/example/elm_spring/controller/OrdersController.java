package org.example.elm_spring.controller;

import org.example.elm_spring.pojo.Business;
import org.example.elm_spring.pojo.OrderDetailet;
import org.example.elm_spring.pojo.Orders;
import org.example.elm_spring.pojo.Result;
import org.example.elm_spring.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单控制器，处理与订单相关的 HTTP 请求。
 * 提供订单创建、明细查询、商家信息查询、订单详情查询、状态更新和删除接口。
 */
@Slf4j
@RestController
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 创建订单。
     * 若该用户在该商家已有未支付订单，返回错误提示；否则创建订单并返回订单 ID。
     * POST /orders
     *
     * @param orders 请求体中的订单信息（包含 userId、businessId、daId、orderTotal）
     * @return 成功时返回订单 ID 的成功响应；失败时返回错误信息
     */
    @PostMapping("/orders")
    public Result addOrders(@RequestBody Orders orders){
        Integer orderId = ordersService.addOrders(orders);
        if (orderId == -1){
            return Result.error("这个商家还有一个订单没有支付,请先支付上一个订单!");
        }else{

            return Result.success(orderId);
        }
    }

    /**
     * 查询指定订单的所有明细（含食品详情）。
     * GET /orderDetailet/{orderId}
     *
     * @param orderId 订单 ID（路径变量）
     * @return 包含订单明细列表的成功响应
     */
    @GetMapping("/orderDetailet/{orderId}")
    public Result getOrderDetailets(@PathVariable Integer orderId){
        List<OrderDetailet> orderDetailetList = ordersService.getOrderDetailets(orderId);
        return Result.success(orderDetailetList);
    }

    /**
     * 按订单 ID 查询该订单所属的商家信息。
     * GET /order/businessInfo/{orderId}
     *
     * @param orderId 订单 ID（路径变量）
     * @return 包含商家信息的成功响应
     */
    @GetMapping("/order/businessInfo/{orderId}")
    public Result getBusinessByOrderId(@PathVariable Integer orderId){
        Business business = ordersService.getBusinessByOrderId(orderId);
        return Result.success(business);
    }

    /**
     * 按订单 ID 查询订单详情（含收货地址）。
     * GET /orders/{orderId}
     *
     * @param orderId 订单 ID（路径变量）
     * @return 包含订单详情的成功响应
     */
    @GetMapping("/orders/{orderId}")
    public Result getOrderByOrderId(@PathVariable Integer orderId){
        Orders orders = ordersService.getOrderByOrderId(orderId);
        return Result.success(orders);
    }

    /**
     * 更新订单状态（如将未支付改为已支付）。
     * PUT /orders
     *
     * @param orders 请求体中包含 orderId 和最新 orderState 的订单对象
     * @return 操作成功响应
     */
    @PutMapping("/orders")
    public Result updateOrders(@RequestBody Orders orders){
        ordersService.updateOrders(orders);
        return Result.success();
    }

    /**
     * 删除指定订单及其所有明细记录。
     * DELETE /orders/{orderId}
     *
     * @param orderId 待删除的订单 ID（路径变量）
     * @return 操作成功响应
     */
    @DeleteMapping("/orders/{orderId}")
    public Result deleteOrders(@PathVariable Integer orderId){
        ordersService.deleteOrders(orderId);
        return Result.success();
    }
}
