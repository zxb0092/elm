package org.example.elm_spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 食品实体类，对应数据库 food 表。
 * 记录商家提供的食品信息，并可关联所属商家及购物车条目。
 *
 * <ul>
 *   <li>{@code foodId} - 食品唯一标识（主键）</li>
 *   <li>{@code foodName} - 食品名称</li>
 *   <li>{@code foodExplain} - 食品描述</li>
 *   <li>{@code foodImg} - 食品图片路径</li>
 *   <li>{@code foodPrice} - 食品价格</li>
 *   <li>{@code businessId} - 所属商家 ID</li>
 *   <li>{@code remarks} - 备注信息</li>
 *   <li>{@code business} - 关联的商家对象（非数据库字段，用于结果封装）</li>
 *   <li>{@code cart} - 关联的购物车条目（非数据库字段，包含用户对该食品的购买数量等）</li>
 * </ul>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    private Integer foodId;
    private String foodName;
    private String foodExplain;
    private String foodImg;
    private Double foodPrice;
    private Integer businessId;
    private String remarks;

    private Business business;

    private Cart cart;
}
