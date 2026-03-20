package org.example.elm_spring.controller;

import org.example.elm_spring.pojo.Cart;
import org.example.elm_spring.pojo.Food;
import org.example.elm_spring.pojo.Result;
import org.example.elm_spring.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 食品控制器，处理与食品相关的 HTTP 请求。
 * 提供食品列表查询（含/不含购物车信息）、购物车条目查询、食品新增和删除接口。
 */
@RestController
public class FoodController {
    @Autowired
    private FoodService foodService;

    /**
     * 查询指定商家下所有食品列表（不关联用户购物车，数量默认为 0）。
     * GET /food/businessId/{businessId}
     *
     * @param businessId 商家 ID（路径变量）
     * @return 包含食品列表的成功响应
     */
    @GetMapping("/food/businessId/{businessId}")
    public Result listFoodByBusinessId(@PathVariable Integer businessId){
        List<Food> foodList = foodService.listFoodByBusinessId(businessId);
        return Result.success(foodList);
    }

    /**
     * 查询指定商家下所有食品列表，并关联当前用户的购物车信息。
     * GET /food/{businessId}/{userId}
     *
     * @param businessId 商家 ID（路径变量）
     * @param userId     用户 ID（路径变量）
     * @return 包含携带购物车信息的食品列表的成功响应
     */
    @GetMapping("/food/{businessId}/{userId}")
    public Result listFood(@PathVariable Integer businessId,@PathVariable Long userId){
        List<Food> foodList = foodService.listFood(businessId,userId);
        return Result.success(foodList);
    }

    /**
     * 查询某用户对某食品的购物车条目（包含 cartId、数量等信息）。
     * GET /food/getCart/{foodId}/{userId}
     *
     * @param foodId 食品 ID（路径变量）
     * @param userId 用户 ID（路径变量）
     * @return 包含购物车条目的成功响应
     */
    @GetMapping("/food/getCart/{foodId}/{userId}")
    public Result getCart(@PathVariable Integer foodId,@PathVariable Long userId){
        Cart cart = foodService.getCart(foodId,userId);
        return Result.success(cart);
    }

    /**
     * 新增食品记录。
     * POST /food
     *
     * @param food 请求体中的食品信息
     * @return 操作成功响应
     */
    @PostMapping("/food")
    public Result addFood(@RequestBody Food food){
        foodService.addFood(food);
        return Result.success();
    }

    /**
     * 按食品 ID 删除食品记录。
     * DELETE /food/{foodId}
     *
     * @param foodId 待删除的食品 ID（路径变量）
     * @return 操作成功响应
     */
    @DeleteMapping("/food/{foodId}")
    public Result deleteFood(@PathVariable Integer foodId){
       foodService.deleteFood(foodId);
        return Result.success();
    }
}
