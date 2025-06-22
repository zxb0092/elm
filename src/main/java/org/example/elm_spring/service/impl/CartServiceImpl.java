package org.example.elm_spring.service.impl;

import org.example.elm_spring.mapper.CartMapper;
import org.example.elm_spring.pojo.Cart;
import org.example.elm_spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public void addCart(Cart cart) {
        cart.setQuantity(1);
        cartMapper.addCart(cart);
    }

    @Override
    public void addQuantity(Integer cartId) {
        cartMapper.addQuantity(cartId);
    }

    @Override
    public void substrateQuantity(Integer cartId) {
        Integer quantity = cartMapper.getCartQuantity(cartId);
        if (quantity > 0) {
            cartMapper.substrateQuantity(cartId);
        }else {
            cartMapper.setQuantity(cartId);
        }
    }

    @Override
    public void deleteAllCart(Long userId) {
        cartMapper.deleteAllCart(userId);
    }
}
