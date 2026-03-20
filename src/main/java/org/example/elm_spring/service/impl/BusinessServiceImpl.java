package org.example.elm_spring.service.impl;

import org.example.elm_spring.mapper.BusinessMapper;
import org.example.elm_spring.pojo.Business;
import org.example.elm_spring.pojo.Cart;
import org.example.elm_spring.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商家业务逻辑实现类，实现 {@link BusinessService} 接口。
 * 通过 {@link BusinessMapper} 执行数据库操作，完成商家相关业务逻辑。
 */
@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    /**
     * 获取所有商家列表。
     *
     * @return 商家列表
     */
    @Override
    public List<Business> list() {
        return businessMapper.list();
    }

    /**
     * 按商家名称模糊搜索商家。
     *
     * @param name 搜索关键词
     * @return 匹配的商家列表
     */
    @Override
    public List<Business> search(String name){
        return businessMapper.search(name);
    }

    /**
     * 按订单分类 ID 查询商家列表。
     *
     * @param orderTypeId 订单分类 ID
     * @return 对应分类下的商家列表
     */
    @Override
    public List<Business> getByOrderTypeId(Integer orderTypeId) {
        return businessMapper.getByOrderTypeId(orderTypeId);
    }

    /**
     * 新增商家记录。
     *
     * @param business 待新增的商家信息
     */
    @Override
    public void add(Business business) {
        businessMapper.add(business);
    }

    /**
     * 删除商家及其下所有食品记录。
     * 先删除 business 表中的商家记录，再删除 food 表中该商家的所有食品。
     *
     * @param businessId 待删除的商家 ID
     */
    @Override
    public void delete(Integer businessId) {
        businessMapper.deleteBusiness(businessId);
        businessMapper.deleteFood(businessId);
    }

    /**
     * 按商家 ID 查询商家详情。
     *
     * @param businessId 商家 ID
     * @return 对应的商家对象
     */
    @Override
    public Business getByBusinessId(Integer businessId) {
        return businessMapper.getByBusinessId(businessId);
    }

    /**
     * 计算指定用户在指定商家购物车中的订单总金额（含配送费）。
     * 遍历购物车中数量不为 0 的条目，按食品单价乘以数量累加，最后加上商家配送费。
     *
     * @param businessId 商家 ID
     * @param userId     用户 ID
     * @return 订单总金额（食品合计 + 配送费）
     */
    @Override
    public Double total(Integer businessId, Long userId) {
        Double total = 0.0;
        List<Cart> cartList = businessMapper.getCartByBusiness(businessId,userId);
        for(Cart cart : cartList){
            Double price =  businessMapper.getPrice(cart.getFoodId());
            total+=price*cart.getQuantity();
        }
        Double deliveryPrice = businessMapper.getByBusinessId(businessId).getDeliveryPrice();
        total+=deliveryPrice;
        return total;
    }

    /**
     * 统计指定用户在指定商家购物车中所有食品的总数量。
     * 遍历购物车中数量不为 0 的条目，累加各食品数量。
     *
     * @param businessId 商家 ID
     * @param userId     用户 ID
     * @return 食品总数量
     */
    @Override
    public Integer getQuantity(Integer businessId, Long userId) {
        Integer quantity = 0;
        List<Cart> cartList = businessMapper.getCartByBusiness(businessId,userId);
        for(Cart cart : cartList){
            quantity+=cart.getQuantity();
        }
        return quantity;
    }
}

