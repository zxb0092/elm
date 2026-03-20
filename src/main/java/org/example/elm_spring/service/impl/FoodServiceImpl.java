package org.example.elm_spring.service.impl;

import org.example.elm_spring.mapper.FoodMapper;
import org.example.elm_spring.pojo.Cart;
import org.example.elm_spring.pojo.Food;
import org.example.elm_spring.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 食品业务逻辑实现类，实现 {@link FoodService} 接口。
 * 通过 {@link FoodMapper} 执行数据库操作，完成食品查询、新增、删除及购物车条目关联的业务逻辑。
 */
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodMapper foodMapper;

    /**
     * 查询指定商家下所有食品列表，不关联用户购物车信息。
     * 为每个食品创建一个空的购物车条目（数量为 0），便于前端统一处理。
     *
     * @param businessId 商家 ID
     * @return 食品列表（每个食品的 cart.quantity 为 0）
     */
    @Override
    public List<Food> listFoodByBusinessId(Integer businessId) {
        List<Food> foodList = foodMapper.listFoodByBusiness(businessId);
        for (Food food : foodList){
            Cart newCart = new Cart();
            newCart.setQuantity(0);
            food.setCart(newCart);
        }
        return foodList;
    }

    /**
     * 查询指定商家下所有食品列表，并关联当前用户的购物车信息（数量、cartId 等）。
     * 为每个食品填充该用户对应的购物车条目，包括 cartId、数量、foodId、businessId、userId。
     *
     * @param businessId 商家 ID
     * @param userId     用户 ID
     * @return 携带购物车信息的食品列表
     */
    @Override
    public List<Food> listFood(Integer businessId,Long userId) {
            List<Food> foodList =foodMapper.listFood(businessId,userId);
            for(Food food :foodList){
                Cart newCart = new Cart();
                newCart.setQuantity(foodMapper.getQuantity(food.getFoodId(),userId));
                newCart.setCartId(foodMapper.getCartId(food.getFoodId(),userId));
                newCart.setFoodId(food.getFoodId());
                newCart.setBusinessId(businessId);
                newCart.setUserId(userId);
                food.setCart(newCart);
            }
            return foodList;
    }

    /**
     * 查询某用户对某食品的购物车条目（包含 cartId、数量等信息）。
     *
     * @param foodId 食品 ID
     * @param userId 用户 ID
     * @return 对应的购物车条目，若不存在则返回 null
     */
    @Override
    public Cart getCart(Integer foodId,Long userId) {
        return foodMapper.getCard(foodId,userId);
    }

    /**
     * 新增食品记录。
     *
     * @param food 待新增的食品对象
     */
    @Override
    public void addFood(Food food) {
        foodMapper.addFood(food);
    }

    /**
     * 按食品 ID 删除食品记录。
     *
     * @param foodId 待删除的食品 ID
     */
    @Override
    public void deleteFood(Integer foodId) {
        foodMapper.deleteFood(foodId);
    }
}
