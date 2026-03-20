package org.example.elm_spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 订单实体类，对应数据库 orders 表。
 * 记录用户在某商家下的订单信息，并关联收货地址、订单明细、购物车列表及商家信息。
 *
 * <ul>
 *   <li>{@code orderId} - 订单唯一标识（主键，随机生成）</li>
 *   <li>{@code userId} - 下单用户 ID</li>
 *   <li>{@code businessId} - 所属商家 ID</li>
 *   <li>{@code orderDate} - 下单时间（格式：yyyy-MM-dd HH:mm:ss）</li>
 *   <li>{@code orderTotal} - 订单总金额（含配送费）</li>
 *   <li>{@code daId} - 关联的收货地址 ID</li>
 *   <li>{@code orderState} - 订单状态（0：未支付，1：已支付）</li>
 *   <li>{@code deliveryAddress} - 关联的收货地址对象（非数据库字段）</li>
 *   <li>{@code orderDetailetList} - 订单明细列表（非数据库字段）</li>
 *   <li>{@code cartList} - 下单时对应的购物车列表（非数据库字段）</li>
 *   <li>{@code business} - 关联的商家信息（非数据库字段）</li>
 * </ul>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private Integer orderId;
    private Long userId;
    private Integer businessId;
    private String orderDate;
    private Double orderTotal;
    private Integer daId;
    private Integer orderState;

    private DeliveryAddress deliveryAddress;
    private List<OrderDetailet> orderDetailetList;
    private List<Cart> cartList;
    private Business business;
}
