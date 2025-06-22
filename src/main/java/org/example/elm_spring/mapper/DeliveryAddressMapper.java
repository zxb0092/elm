package org.example.elm_spring.mapper;

import org.apache.ibatis.annotations.*;
import org.example.elm_spring.pojo.DeliveryAddress;

import java.util.List;

@Mapper
public interface DeliveryAddressMapper {
    @Select("select * from deliveryaddress where userId = #{userId}")
    List<DeliveryAddress> list(Long userId);

    @Insert("insert into deliveryaddress(contactName, contactSex, contactTel, address, userId) values (#{contactName}, #{contactSex}, #{contactTel}, #{address}, #{userId})")
    void addAddress(DeliveryAddress deliveryAddress);

    @Delete("delete from deliveryaddress where daId=#{daId}")
    void deleteAddress(Integer daId);

    @Update("UPDATE deliveryaddress SET contactName = #{contactName}, contactSex = #{contactSex}, contactTel = #{contactTel}, address = #{address} WHERE daId = #{daId}")
    void updateAddress(DeliveryAddress deliveryAddress);

    @Select("select * from deliveryaddress where daId = #{daId}")
    DeliveryAddress getByDaId(Integer daId);
}
