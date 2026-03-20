package org.example.elm_spring.service.impl;

import org.example.elm_spring.mapper.CartMapper;
import org.example.elm_spring.pojo.Cart;
import org.example.elm_spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 购物车业务逻辑实现类，实现 {@link CartService} 接口。
 * 通过 {@link CartMapper} 执行数据库操作，完成购物车相关业务逻辑。
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    /**
     * 向购物车中添加食品条目，初始数量固定设为 1。
     *
     * @param cart 待添加的购物车条目（包含 foodId、businessId、userId）
     */
    @Override
    public void addCart(Cart cart) {
        cart.setQuantity(1);
        cartMapper.addCart(cart);
    }

    /**
     * 将指定购物车条目的数量加 1。
     *
     * @param cartId 购物车条目 ID
     */
    @Override
    public void addQuantity(Integer cartId) {
        cartMapper.addQuantity(cartId);
    }

    /**
     * 将指定购物车条目的数量减 1。
     * 若当前数量大于 0，则执行减 1 操作；否则将数量置为 0（防止出现负数）。
     *
     * @param cartId 购物车条目 ID
     */
    @Override
    public void substrateQuantity(Integer cartId) {
        Integer quantity = cartMapper.getCartQuantity(cartId);
        if (quantity > 0) {
            cartMapper.substrateQuantity(cartId);
        }else {
            cartMapper.setQuantity(cartId);
        }
    }

    /**
     * 删除指定用户的所有购物车条目。
     *
     * @param userId 用户 ID
     */
    @Override
    public void deleteAllCart(Long userId) {
        cartMapper.deleteAllCart(userId);
    }
}
