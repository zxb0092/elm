package org.example.elm_spring.service;

import org.example.elm_spring.pojo.Cart;

/**
 * 购物车业务逻辑接口。
 * 定义购物车的新增、数量调整及清空操作。
 */
public interface CartService {

    /**
     * 向购物车中添加食品条目，初始数量设为 1。
     *
     * @param cart 待添加的购物车条目（包含 foodId、businessId、userId）
     */
    void addCart(Cart cart);

    /**
     * 将指定购物车条目的数量加 1。
     *
     * @param cartId 购物车条目 ID
     */
    void addQuantity(Integer cartId);

    /**
     * 将指定购物车条目的数量减 1；若当前数量已为 0，则将数量置为 0（不减为负数）。
     *
     * @param cartId 购物车条目 ID
     */
    void substrateQuantity(Integer cartId);

    /**
     * 删除指定用户的所有购物车条目。
     *
     * @param userId 用户 ID
     */
    void deleteAllCart(Long userId);
}
