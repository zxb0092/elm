package org.example.elm_spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 商家实体类，对应数据库 business 表。
 * 包含商家的基本信息，以及与食品列表和购物车列表的一对多关联关系。
 *
 * <ul>
 *   <li>{@code businessId} - 商家唯一标识（主键）</li>
 *   <li>{@code businessName} - 商家名称</li>
 *   <li>{@code businessAddress} - 商家地址</li>
 *   <li>{@code businessExplain} - 商家描述</li>
 *   <li>{@code businessImg} - 商家图片路径</li>
 *   <li>{@code orderTypeId} - 订单分类 ID（用于按分类筛选商家）</li>
 *   <li>{@code starPrice} - 起送价格</li>
 *   <li>{@code deliveryPrice} - 配送费用</li>
 *   <li>{@code remarks} - 备注信息</li>
 *   <li>{@code foodList} - 该商家下的食品列表（一对多）</li>
 *   <li>{@code cartList} - 该商家下用户的购物车列表（一对多）</li>
 * </ul>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Business {
    private Integer businessId;
    private String businessName;
    private String businessAddress;
    private String businessExplain;
    private String businessImg;
    private Integer orderTypeId;
    private double starPrice;
    private double deliveryPrice;
    private String remarks;
    //一对多关系
    private List<Food> foodList;

    private List<Cart> cartList;
}
