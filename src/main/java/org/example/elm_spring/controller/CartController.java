package org.example.elm_spring.controller;

import org.example.elm_spring.pojo.Cart;
import org.example.elm_spring.pojo.Result;
import org.example.elm_spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车控制器，处理与购物车相关的 HTTP 请求。
 * 提供购物车条目新增及数量增减接口。
 */
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * 向购物车中添加食品条目（初始数量为 1）。
     * POST /cart
     *
     * @param cart 请求体中的购物车条目信息（包含 foodId、businessId、userId）
     * @return 操作成功响应
     */
    @PostMapping("/cart")
    public Result addCart(@RequestBody Cart cart){
        cartService.addCart(cart);
        return Result.success();
    }

    /**
     * 将指定购物车条目的数量加 1。
     * PUT /cart/addQuantity/{cartId}
     *
     * @param cartId 购物车条目 ID（路径变量）
     * @return 操作成功响应
     */
    @PutMapping("/cart/addQuantity/{cartId}")
    public Result addQuantity(@PathVariable Integer cartId){
        cartService.addQuantity(cartId);
        return Result.success();
    }

    /**
     * 将指定购物车条目的数量减 1（最低为 0）。
     * PUT /cart/substrateQuantity/{cartId}
     *
     * @param cartId 购物车条目 ID（路径变量）
     * @return 操作成功响应
     */
    @PutMapping("/cart/substrateQuantity/{cartId}")
    public Result substrateQuantity(@PathVariable Integer cartId){
        cartService.substrateQuantity(cartId);
        return Result.success();
    }
}
