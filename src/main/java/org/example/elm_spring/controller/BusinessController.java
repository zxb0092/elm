package org.example.elm_spring.controller;

import org.example.elm_spring.pojo.Business;
import org.example.elm_spring.pojo.Result;
import org.example.elm_spring.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商家控制器，处理与商家相关的 HTTP 请求。
 * 提供商家列表查询、搜索、分类查询、详情查询、新增、删除以及购物车统计接口。
 */
@RestController
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    /**
     * 获取所有商家列表。
     * GET /business
     *
     * @return 包含所有商家信息的成功响应
     */
    @GetMapping("/business")
    public Result list(){
        List<Business> bussinessList = businessService.list();
        return Result.success(bussinessList);
    }

    /**
     * 按商家名称模糊搜索商家。
     * GET /business/search/{name}
     *
     * @param name 搜索关键词（路径变量）
     * @return 包含匹配商家列表的成功响应
     */
    @GetMapping("/business/search/{name}")
    public Result search(@PathVariable String  name){
        List<Business> businessList = businessService.search(name);
        return Result.success(businessList);
    }

    /**
     * 按订单分类 ID 查询商家列表。
     * GET /business/orderTypeId/{orderTypeId}
     *
     * @param orderTypeId 订单分类 ID（路径变量）
     * @return 包含对应分类下商家列表的成功响应
     */
    @GetMapping("/business/orderTypeId/{orderTypeId}")
    public Result getByOrderTypeId(@PathVariable Integer orderTypeId){
        List<Business> businessList = businessService.getByOrderTypeId(orderTypeId);
        return Result.success(businessList);
    }

    /**
     * 按商家 ID 查询商家详情。
     * GET /business/businessId/{businessId}
     *
     * @param businessId 商家 ID（路径变量）
     * @return 包含对应商家详情的成功响应
     */
    @GetMapping("/business/businessId/{businessId}")
    public Result getByBusinessId(@PathVariable Integer businessId){
        Business business = businessService.getByBusinessId(businessId);
        return Result.success(business);
    }

    /**
     * 新增商家记录。
     * POST /business
     *
     * @param business 请求体中的商家信息
     * @return 操作成功响应
     */
    @PostMapping("/business")
    public Result add(@RequestBody Business business) {
        businessService.add(business);
        // 返回成功的结果
        return Result.success();
    }

    /**
     * 删除指定商家及其下所有食品。
     * DELETE /business/{businessId}
     *
     * @param businessId 待删除的商家 ID（路径变量）
     * @return 操作成功响应
     */
    @DeleteMapping("/business/{businessId}")
    public Result delete(@PathVariable Integer businessId){
        businessService.delete(businessId);
        return Result.success();
    }

    /**
     * 计算指定用户在指定商家购物车中的订单总金额（含配送费）。
     * GET /business/total/{businessId}/{userId}
     *
     * @param businessId 商家 ID（路径变量）
     * @param userId     用户 ID（路径变量）
     * @return 包含订单总金额的成功响应
     */
    @GetMapping("/business/total/{businessId}/{userId}")
    public Result total(@PathVariable Integer businessId,@PathVariable Long userId){
        Double total =  businessService.total(businessId,userId);
        return Result.success(total);
    }

    /**
     * 统计指定用户在指定商家购物车中所有食品的总数量。
     * GET /business/quantity/{businessId}/{userId}
     *
     * @param businessId 商家 ID（路径变量）
     * @param userId     用户 ID（路径变量）
     * @return 包含食品总数量的成功响应
     */
    @GetMapping("/business/quantity/{businessId}/{userId}")
    public Result quantity(@PathVariable Integer businessId,@PathVariable Long userId){
        Integer quantity = businessService.getQuantity(businessId,userId);
        return Result.success(quantity);
    }
}
