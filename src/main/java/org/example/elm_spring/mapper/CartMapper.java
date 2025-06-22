package org.example.elm_spring.mapper;

import org.apache.ibatis.annotations.*;
import org.example.elm_spring.pojo.Cart;

@Mapper
public interface CartMapper {
    @Insert("insert into cart(foodId, businessId, userId, quantity)" +
            "values(#{foodId}, #{businessId}, #{userId}, #{quantity})")
    void addCart(Cart cart);

    @Update("update cart set quantity = quantity + 1 " +
            "where cartId=#{cartId}")
    void addQuantity(Integer cartId);

    @Update("update cart set quantity = quantity - 1 " +
            "where cartId=#{cartId}")
    void substrateQuantity(Integer cartId);

    @Select("select quantity from cart where cartId = #{cartId}")
    Integer getCartQuantity(Integer cartId);

    @Update("update cart set quantity = 0 " +
            "where cartId=#{cartId}")
    void setQuantity(Integer cartId);

    @Delete("DELETE FROM cart WHERE userId = #{userId}")
    void deleteAllCart(Long userId);
}
