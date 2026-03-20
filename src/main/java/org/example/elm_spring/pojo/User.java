package org.example.elm_spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户实体类，对应数据库 user 表。
 * 记录平台用户的基本信息，并可关联其收货地址列表和登录 token。
 *
 * <ul>
 *   <li>{@code userId} - 用户唯一标识（主键，使用 UUID 最高有效位生成）</li>
 *   <li>{@code password} - 用户登录密码</li>
 *   <li>{@code userName} - 用户名（唯一）</li>
 *   <li>{@code userSex} - 性别（0：女，1：男）</li>
 *   <li>{@code userImg} - 用户头像图片路径</li>
 *   <li>{@code delTag} - 删除标志（1：有效，0：已删除）</li>
 *   <li>{@code token} - 登录成功后生成的 JWT token（非数据库字段）</li>
 *   <li>{@code deliveryAddressList} - 用户的收货地址列表（非数据库字段）</li>
 * </ul>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;
    private String password;
    private String userName;
    private Integer userSex;
    private String userImg;
    private Integer delTag;

    private String token;
    private List<DeliveryAddress> deliveryAddressList;
}
