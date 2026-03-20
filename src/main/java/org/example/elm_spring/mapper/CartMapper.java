package org.example.elm_spring.mapper;

import org.apache.ibatis.annotations.*;
import org.example.elm_spring.pojo.Cart;

/**
 * 购物车数据访问接口（MyBatis Mapper）。
 * 提供对 cart 表的增删改查操作。
 */
@Mapper
public interface CartMapper {

    /**
     * 新增购物车条目。
     *
     * @param cart 待新增的购物车对象（包含 foodId、businessId、userId、quantity）
     */
    @Insert("insert into cart(foodId, businessId, userId, quantity)" +
            "values(#{foodId}, #{businessId}, #{userId}, #{quantity})")
    void addCart(Cart cart);

    /**
     * 将指定购物车条目的数量加 1。
     *
     * @param cartId 购物车条目 ID
     */
    @Update("update cart set quantity = quantity + 1 " +
            "where cartId=#{cartId}")
    void addQuantity(Integer cartId);

    /**
     * 将指定购物车条目的数量减 1。
     *
     * @param cartId 购物车条目 ID
     */
    @Update("update cart set quantity = quantity - 1 " +
            "where cartId=#{cartId}")
    void substrateQuantity(Integer cartId);

    /**
     * 查询指定购物车条目的当前数量。
     *
     * @param cartId 购物车条目 ID
     * @return 当前数量
     */
    @Select("select quantity from cart where cartId = #{cartId}")
    Integer getCartQuantity(Integer cartId);

    /**
     * 将指定购物车条目的数量重置为 0。
     *
     * @param cartId 购物车条目 ID
     */
    @Update("update cart set quantity = 0 " +
            "where cartId=#{cartId}")
    void setQuantity(Integer cartId);

    /**
     * 删除指定用户的所有购物车条目。
     *
     * @param userId 用户 ID
     */
    @Delete("DELETE FROM cart WHERE userId = #{userId}")
    void deleteAllCart(Long userId);
}
