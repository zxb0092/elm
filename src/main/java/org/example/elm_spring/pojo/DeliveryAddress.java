package org.example.elm_spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 收货地址实体类，对应数据库 deliveryaddress 表。
 * 保存用户的收货地址及联系人信息。
 *
 * <ul>
 *   <li>{@code daId} - 地址唯一标识（主键）</li>
 *   <li>{@code contactName} - 收货联系人姓名</li>
 *   <li>{@code contactSex} - 联系人性别（0：女，1：男）</li>
 *   <li>{@code contactTel} - 联系人电话</li>
 *   <li>{@code address} - 收货地址详情</li>
 *   <li>{@code userId} - 所属用户 ID</li>
 *   <li>{@code user} - 关联的用户对象（非数据库字段，用于结果封装）</li>
 * </ul>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddress {
    private Integer daId;
    private String contactName;
    private Integer contactSex;
    private String contactTel;
    private String address;
    private Long userId;

    private User user;
}
