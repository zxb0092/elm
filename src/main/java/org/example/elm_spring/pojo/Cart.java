package org.example.elm_spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 购物车实体类，对应数据库 cart 表。
 * 记录某用户在某商家下某食品的购物车条目信息。
 *
 * <ul>
 *   <li>{@code cartId} - 购物车条目唯一标识（主键）</li>
 *   <li>{@code foodId} - 关联的食品 ID</li>
 *   <li>{@code businessId} - 关联的商家 ID</li>
 *   <li>{@code userId} - 关联的用户 ID</li>
 *   <li>{@code quantity} - 该食品的购买数量</li>
 *   <li>{@code food} - 关联的食品详情对象（非数据库字段，用于结果封装）</li>
 * </ul>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Integer cartId;
    private Integer foodId;
    private Integer businessId;
    private Long userId;
    private Integer quantity;

    private Food food;
}
