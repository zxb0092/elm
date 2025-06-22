package org.example.elm_spring.service;

import org.example.elm_spring.pojo.Cart;
import org.example.elm_spring.pojo.Food;

import java.util.List;

public interface FoodService {
    List<Food> listFoodByBusinessId(Integer businessId);
    List<Food> listFood(Integer businessId,Long userId);

    Cart getCart(Integer foodId,Long userId);

    void addFood(Food food);

    void deleteFood(Integer foodId);

}
