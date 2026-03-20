package org.example.elm_spring.service;

import org.example.elm_spring.pojo.Business;

import java.util.List;

/**
 * 商家业务逻辑接口。
 * 定义商家相关的查询、新增、删除以及购物车统计方法。
 */
public interface BusinessService {

    /**
     * 获取所有商家列表。
     *
     * @return 商家列表
     */
    List<Business> list();

    /**
     * 按商家名称模糊搜索商家。
     *
     * @param name 搜索关键词
     * @return 匹配的商家列表
     */
    List<Business> search(String name);

    /**
     * 按订单分类 ID 查询商家列表。
     *
     * @param orderTypeId 订单分类 ID
     * @return 对应分类下的商家列表
     */
    List<Business> getByOrderTypeId(Integer orderTypeId);

    /**
     * 新增商家。
     *
     * @param business 待新增的商家信息
     */
    void add(Business business);

    /**
     * 删除商家及其下所有食品。
     *
     * @param businessId 待删除的商家 ID
     */
    void delete(Integer businessId);

    /**
     * 按商家 ID 查询商家详情。
     *
     * @param businessId 商家 ID
     * @return 对应的商家对象
     */
    Business getByBusinessId(Integer businessId);

    /**
     * 计算指定用户在指定商家购物车中的订单总金额（含配送费）。
     *
     * @param businessId 商家 ID
     * @param userId     用户 ID
     * @return 订单总金额
     */
    Double total(Integer businessId, Long userId);

    /**
     * 统计指定用户在指定商家购物车中所有食品的总数量。
     *
     * @param businessId 商家 ID
     * @param userId     用户 ID
     * @return 食品总数量
     */
    Integer getQuantity(Integer businessId, Long userId);
}
