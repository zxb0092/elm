package org.example.elm_spring.mapper;

import org.apache.ibatis.annotations.*;
import org.example.elm_spring.pojo.DeliveryAddress;

import java.util.List;

/**
 * 收货地址数据访问接口（MyBatis Mapper）。
 * 提供对 deliveryaddress 表的增删改查操作。
 */
@Mapper
public interface DeliveryAddressMapper {

    /**
     * 查询指定用户的所有收货地址。
     *
     * @param userId 用户 ID
     * @return 收货地址列表
     */
    @Select("select * from deliveryaddress where userId = #{userId}")
    List<DeliveryAddress> list(Long userId);

    /**
     * 新增收货地址记录。
     *
     * @param deliveryAddress 待新增的收货地址对象
     */
    @Insert("insert into deliveryaddress(contactName, contactSex, contactTel, address, userId) values (#{contactName}, #{contactSex}, #{contactTel}, #{address}, #{userId})")
    void addAddress(DeliveryAddress deliveryAddress);

    /**
     * 按地址 ID 删除收货地址。
     *
     * @param daId 收货地址 ID
     */
    @Delete("delete from deliveryaddress where daId=#{daId}")
    void deleteAddress(Integer daId);

    /**
     * 更新指定收货地址的联系人信息和地址详情。
     *
     * @param deliveryAddress 包含最新信息的收货地址对象（需包含 daId）
     */
    @Update("UPDATE deliveryaddress SET contactName = #{contactName}, contactSex = #{contactSex}, contactTel = #{contactTel}, address = #{address} WHERE daId = #{daId}")
    void updateAddress(DeliveryAddress deliveryAddress);

    /**
     * 按地址 ID 查询单个收货地址。
     *
     * @param daId 收货地址 ID
     * @return 对应的收货地址对象，若不存在则返回 null
     */
    @Select("select * from deliveryaddress where daId = #{daId}")
    DeliveryAddress getByDaId(Integer daId);
}
