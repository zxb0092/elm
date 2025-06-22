package org.example.elm_spring.service;

import org.example.elm_spring.pojo.Cart;

public interface CartService {
    void addCart(Cart cart);

    void addQuantity(Integer cartId);

    void substrateQuantity(Integer cartId);


    void deleteAllCart(Long userId);
}
