package org.example.elm_spring.service.impl;

import org.example.elm_spring.mapper.UserMapper;
import org.example.elm_spring.pojo.*;
import org.example.elm_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 用户业务逻辑实现类，实现 {@link UserService} 接口。
 * 通过 {@link UserMapper} 执行数据库操作，完成用户信息查询、登录、注册及订单查询的业务逻辑。
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 按用户 ID 查询用户详情。
     *
     * @param userId 用户 ID
     * @return 对应的用户对象
     */
    @Override
    public User getUserInfo(Long userId) {
        return userMapper.getUserInfo(userId);
    }

    /**
     * 用户登录验证，根据用户名和密码查询用户。
     *
     * @param user 包含 userName 和 password 的用户对象
     * @return 登录成功返回用户对象，否则返回 null
     */
    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    /**
     * 用户注册。
     * 先按用户名查询是否已存在同名用户：
     * <ul>
     *   <li>若不存在，设置 delTag 为 1（有效），使用 UUID 最高有效位生成唯一 userId，
     *       调用 Mapper 插入用户记录并返回新用户对象；</li>
     *   <li>若已存在，返回 null 表示注册失败。</li>
     * </ul>
     *
     * @param user 待注册的用户信息（包含 userName、password、userSex、userImg）
     * @return 注册成功返回新用户对象，否则返回 null
     */
    @Override
    public User register(User user) {
        User newUser = userMapper.getUserByUserName(user.getUserName());
        if (newUser == null){
            user.setDelTag(1);
            user.setUserId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
            userMapper.register(user);
            return user;
        }else {
            return null;
        }
    }

    /**
     * 查询指定用户的所有未支付订单，并填充每个订单的商家信息和带食品详情的订单明细列表。
     *
     * @param userId 用户 ID
     * @return 未支付订单列表（每个订单包含 business 和 orderDetailetList）
     */
    @Override
    public List<Orders> getOrdersNotPay(Long userId) {
        List<Orders> ordersList = userMapper.getOrdersNotPay(userId);
        for(Orders orders : ordersList){
            Business newBusiness = userMapper.getBusiness(orders.getBusinessId());
            orders.setBusiness(newBusiness);
            List<OrderDetailet> orderDetailetList = userMapper.getOrderDetailet(orders.getOrderId());
            for(OrderDetailet orderDetailet : orderDetailetList){
                Food newFood = userMapper.getFood(orderDetailet.getFoodId());
                orderDetailet.setFood(newFood);
            }
            orders.setOrderDetailetList(orderDetailetList);
        }
        return ordersList;
    }

    /**
     * 查询指定用户的所有已支付订单，并填充每个订单的商家信息和带食品详情的订单明细列表。
     *
     * @param userId 用户 ID
     * @return 已支付订单列表（每个订单包含 business 和 orderDetailetList）
     */
    @Override
    public List<Orders> getOrdersPay(Long userId) {
        List<Orders> ordersList = userMapper.getOrdersPay(userId);
        for(Orders orders : ordersList){
            Business newBusiness = userMapper.getBusiness(orders.getBusinessId());
            orders.setBusiness(newBusiness);
            List<OrderDetailet> orderDetailetList = userMapper.getOrderDetailet(orders.getOrderId());
            for(OrderDetailet orderDetailet : orderDetailetList){
                Food newFood = userMapper.getFood(orderDetailet.getFoodId());
                orderDetailet.setFood(newFood);
            }
            orders.setOrderDetailetList(orderDetailetList);
        }
        return ordersList;
    }
}
