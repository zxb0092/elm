package org.example.elm_spring.service.impl;

import org.example.elm_spring.mapper.DeliveryAddressMapper;
import org.example.elm_spring.pojo.DeliveryAddress;
import org.example.elm_spring.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址业务逻辑实现类，实现 {@link DeliveryAddressService} 接口。
 * 通过 {@link DeliveryAddressMapper} 执行数据库操作，完成收货地址的增删改查业务逻辑。
 */
@Service
public class DeliveryAddressImpl implements DeliveryAddressService {
    @Autowired
    private DeliveryAddressMapper deliveryAddressMapper;

    /**
     * 查询指定用户的所有收货地址。
     *
     * @param userId 用户 ID
     * @return 收货地址列表
     */
    @Override
    public List<DeliveryAddress> list(Long userId) {
        return deliveryAddressMapper.list(userId);
    }

    /**
     * 新增收货地址记录。
     *
     * @param deliveryAddress 待新增的收货地址对象
     */
    @Override
    public void addAddress(DeliveryAddress deliveryAddress) {
        deliveryAddressMapper.addAddress(deliveryAddress);
    }

    /**
     * 按地址 ID 删除收货地址。
     *
     * @param daId 收货地址 ID
     */
    @Override
    public void deleteAddress(Integer daId) {
        deliveryAddressMapper.deleteAddress(daId);
    }

    /**
     * 更新收货地址信息。
     *
     * @param deliveryAddress 包含最新信息的收货地址对象（需包含 daId）
     */
    @Override
    public void updateAddress(DeliveryAddress deliveryAddress) {
        deliveryAddressMapper.updateAddress(deliveryAddress);
    }

    /**
     * 按地址 ID 查询单个收货地址。
     *
     * @param daId 收货地址 ID
     * @return 对应的收货地址对象
     */
    @Override
    public DeliveryAddress getByDaId(Integer daId) {
        return deliveryAddressMapper.getByDaId(daId);
    }
}
