package org.example.elm_spring.controller;

import org.example.elm_spring.pojo.DeliveryAddress;
import org.example.elm_spring.pojo.Result;
import org.example.elm_spring.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收货地址控制器，处理与收货地址相关的 HTTP 请求。
 * 提供地址列表查询、新增、删除、更新及单个地址查询接口。
 */
@RestController
public class DeliveryAddressController {
    @Autowired
    private DeliveryAddressService deliveryAddressService;

    /**
     * 查询指定用户的所有收货地址列表。
     * GET /addressList/{userId}
     *
     * @param userId 用户 ID（路径变量）
     * @return 包含收货地址列表的成功响应
     */
    @GetMapping("/addressList/{userId}")
    public Result list(@PathVariable Long userId){
        List<DeliveryAddress> deliveryAddressList = deliveryAddressService.list(userId);
        return Result.success(deliveryAddressList);
    }

    /**
     * 新增收货地址。
     * POST /addressList
     *
     * @param deliveryAddress 请求体中的收货地址信息
     * @return 操作成功响应
     */
    @PostMapping("/addressList")
    public Result addAddress(@RequestBody DeliveryAddress deliveryAddress){
        deliveryAddressService.addAddress(deliveryAddress);
        return Result.success();
    }

    /**
     * 按地址 ID 删除收货地址。
     * DELETE /addressList/{daId}
     *
     * @param daId 收货地址 ID（路径变量）
     * @return 操作成功响应
     */
    @DeleteMapping("/addressList/{daId}")
    public Result deleteAddress(@PathVariable Integer daId){
        deliveryAddressService.deleteAddress(daId);
        return Result.success();
    }

    /**
     * 更新收货地址信息。
     * PUT /addressList
     *
     * @param deliveryAddress 请求体中包含最新信息的收货地址对象（需包含 daId）
     * @return 操作成功响应
     */
    @PutMapping("/addressList")
    public Result updateAddress(@RequestBody DeliveryAddress deliveryAddress){
        deliveryAddressService.updateAddress(deliveryAddress);
        return Result.success();
    }

    /**
     * 按地址 ID 查询单个收货地址详情。
     * GET /addressList/daId/{daId}
     *
     * @param daId 收货地址 ID（路径变量）
     * @return 包含对应收货地址的成功响应
     */
    @GetMapping("/addressList/daId/{daId}")
    public Result getByDaId(@PathVariable Integer daId){
        DeliveryAddress deliveryAddress = deliveryAddressService.getByDaId(daId);
        return Result.success(deliveryAddress);
    }
}
