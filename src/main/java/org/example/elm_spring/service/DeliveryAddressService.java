package org.example.elm_spring.service;

import org.example.elm_spring.pojo.DeliveryAddress;

import java.util.List;

/**
 * 收货地址业务逻辑接口。
 * 定义收货地址的查询、新增、删除、更新操作。
 */
public interface DeliveryAddressService {

    /**
     * 查询指定用户的所有收货地址。
     *
     * @param userId 用户 ID
     * @return 收货地址列表
     */
    List<DeliveryAddress> list(Long userId);

    /**
     * 新增收货地址。
     *
     * @param deliveryAddress 待新增的收货地址对象
     */
    void addAddress(DeliveryAddress deliveryAddress);

    /**
     * 按地址 ID 删除收货地址。
     *
     * @param daId 收货地址 ID
     */
    void deleteAddress(Integer daId);

    /**
     * 更新收货地址信息。
     *
     * @param deliveryAddress 包含最新信息的收货地址对象（需包含 daId）
     */
    void updateAddress(DeliveryAddress deliveryAddress);

    /**
     * 按地址 ID 查询单个收货地址。
     *
     * @param daId 收货地址 ID
     * @return 对应的收货地址对象
     */
    DeliveryAddress getByDaId(Integer daId);
}
