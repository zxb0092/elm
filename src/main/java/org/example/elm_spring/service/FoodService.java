package org.example.elm_spring.service;

import org.example.elm_spring.pojo.Cart;
import org.example.elm_spring.pojo.Food;

import java.util.List;

/**
 * 食品业务逻辑接口。
 * 定义食品的查询、新增、删除及购物车条目查询操作。
 */
public interface FoodService {

    /**
     * 查询指定商家下所有食品列表（不关联用户购物车，数量默认为 0）。
     *
     * @param businessId 商家 ID
     * @return 食品列表
     */
    List<Food> listFoodByBusinessId(Integer businessId);

    /**
     * 查询指定商家下所有食品列表，并关联当前用户的购物车信息（数量、cartId 等）。
     *
     * @param businessId 商家 ID
     * @param userId     用户 ID
     * @return 携带购物车信息的食品列表
     */
    List<Food> listFood(Integer businessId, Long userId);

    /**
     * 查询某用户对某食品的购物车条目（包含 cartId、数量等信息）。
     *
     * @param foodId 食品 ID
     * @param userId 用户 ID
     * @return 对应的购物车条目，若不存在则返回 null
     */
    Cart getCart(Integer foodId, Long userId);

    /**
     * 新增食品记录。
     *
     * @param food 待新增的食品对象
     */
    void addFood(Food food);

    /**
     * 按食品 ID 删除食品记录。
     *
     * @param foodId 待删除的食品 ID
     */
    void deleteFood(Integer foodId);
}
