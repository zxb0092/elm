package org.example.elm_spring.controller;

import org.example.elm_spring.pojo.Cart;
import org.example.elm_spring.pojo.Food;
import org.example.elm_spring.pojo.Result;
import org.example.elm_spring.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/food/businessId/{businessId}")
    public Result listFoodByBusinessId(@PathVariable Integer businessId){
        List<Food> foodList = foodService.listFoodByBusinessId(businessId);
        return Result.success(foodList);
    }

    @GetMapping("/food/{businessId}/{userId}")
    public Result listFood(@PathVariable Integer businessId,@PathVariable Long userId){
        List<Food> foodList = foodService.listFood(businessId,userId);
        return Result.success(foodList);
    }

    @GetMapping("/food/getCart/{foodId}/{userId}")
    public Result getCart(@PathVariable Integer foodId,@PathVariable Long userId){
        Cart cart = foodService.getCart(foodId,userId);
        return Result.success(cart);
    }

    @PostMapping("/food")
    public Result addFood(@RequestBody Food food){
        foodService.addFood(food);
        return Result.success();
    }
    @DeleteMapping("/food/{foodId}")
    public Result deleteFood(@PathVariable Integer foodId){
       foodService.deleteFood(foodId);
        return Result.success();
    }

}
