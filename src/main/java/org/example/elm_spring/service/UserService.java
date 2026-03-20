package org.example.elm_spring.service;

import org.example.elm_spring.pojo.Orders;
import org.example.elm_spring.pojo.User;

import java.util.List;

/**
 * 用户业务逻辑接口。
 * 定义用户信息查询、登录、注册以及订单查询操作。
 */
public interface UserService {

    /**
     * 按用户 ID 查询用户详情。
     *
     * @param userId 用户 ID
     * @return 对应的用户对象
     */
    User getUserInfo(Long userId);

    /**
     * 用户登录验证。
     * 根据用户名和密码查询用户，成功则返回用户对象，失败则返回 null。
     *
     * @param user 包含 userName 和 password 的用户对象
     * @return 登录成功返回用户对象，否则返回 null
     */
    User login(User user);

    /**
     * 用户注册。
     * 检查用户名是否已存在：若不存在则生成唯一 userId 并入库，返回新用户对象；
     * 若已存在则返回 null。
     *
     * @param user 待注册的用户信息（包含 userName、password、userSex、userImg）
     * @return 注册成功返回新用户对象，否则返回 null
     */
    User register(User user);

    /**
     * 查询指定用户的所有未支付订单，并填充每个订单的商家信息和带食品详情的订单明细列表。
     *
     * @param userId 用户 ID
     * @return 未支付订单列表
     */
    List<Orders> getOrdersNotPay(Long userId);

    /**
     * 查询指定用户的所有已支付订单，并填充每个订单的商家信息和带食品详情的订单明细列表。
     *
     * @param userId 用户 ID
     * @return 已支付订单列表
     */
    List<Orders> getOrdersPay(Long userId);
}
