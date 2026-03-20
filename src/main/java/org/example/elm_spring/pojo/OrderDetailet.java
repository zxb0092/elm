package org.example.elm_spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单明细实体类，对应数据库 orderdetailet 表（注：表名存在拼写特殊性，对应 orderdetail 语义）。
 * 记录某订单中每种食品的购买详情。
 *
 * <ul>
 *   <li>{@code odId} - 订单明细唯一标识（主键）</li>
 *   <li>{@code orderId} - 所属订单 ID</li>
 *   <li>{@code foodId} - 关联的食品 ID</li>
 *   <li>{@code quantity} - 该食品的购买数量</li>
 *   <li>{@code food} - 关联的食品详情对象（非数据库字段，用于结果封装）</li>
 * </ul>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailet {
    private Integer odId;
    private Integer orderId;
    private Integer foodId;
    private Integer quantity;

    private Food food;
}
