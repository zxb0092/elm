package org.example.elm_spring.controller;

import org.example.elm_spring.pojo.Orders;
import org.example.elm_spring.pojo.Result;
import org.example.elm_spring.pojo.User;
import org.example.elm_spring.service.UserService;
import org.example.elm_spring.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器，处理与用户相关的 HTTP 请求。
 * 提供用户信息查询、登录、注册以及订单列表查询接口。
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 按用户 ID 查询用户详情。
     * GET /userInfo/{userId}
     *
     * @param userId 用户 ID（路径变量）
     * @return 包含用户详情的成功响应
     */
    @GetMapping("/userInfo/{userId}")
    public Result getUserInfo(@PathVariable Long userId){
        User user = userService.getUserInfo(userId);
        return Result.success(user);
    }

    /**
     * 用户登录接口。
     * 根据用户名和密码验证用户，成功后生成 JWT token 并设置到用户对象中一并返回；
     * 失败则返回"用户名或密码错误"的错误响应。
     * POST /login
     *
     * @param user 请求体中包含 userName 和 password 的用户对象
     * @return 登录成功返回含 token 的用户信息；失败返回错误响应
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User user1 =  userService.login(user);
        JwtUtils jwtUtils = new JwtUtils();
        if(user1!=null){
            Map<String,Object> map = new HashMap<>();
            map.put("userId", user1.getUserId());
            map.put("userName",user1.getUserName());
            String token = jwtUtils.createToken(map);
            user1.setToken(token);
            return Result.success(user1);
        }else {
            return Result.error("用户名或密码错误");
        }

    }

    /**
     * 用户注册接口。
     * 若用户名未被注册，创建新用户并返回；否则返回"该用户名已被注册"的错误响应。
     * POST /register
     *
     * @param user 请求体中待注册的用户信息（包含 userName、password、userSex、userImg）
     * @return 注册成功返回新用户对象；失败返回错误响应
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        User newUser = userService.register(user);
        if (newUser != null){
            return Result.success(newUser);
        }else {
            return Result.error("该用户名已被注册");
        }

    }

    /**
     * 查询指定用户的所有未支付订单列表（含商家信息和订单明细）。
     * GET /orderListNotPay/{userId}
     *
     * @param userId 用户 ID（路径变量）
     * @return 包含未支付订单列表的成功响应
     */
    @GetMapping("/orderListNotPay/{userId}")
    public Result getOrdersByUserIdNotPay(@PathVariable Long userId){
        List<Orders> ordersList = userService.getOrdersNotPay(userId);
        return Result.success(ordersList);
    }

    /**
     * 查询指定用户的所有已支付订单列表（含商家信息和订单明细）。
     * GET /orderListPay/{userId}
     *
     * @param userId 用户 ID（路径变量）
     * @return 包含已支付订单列表的成功响应
     */
    @GetMapping("/orderListPay/{userId}")
    public Result getOrdersByUserIdPay(@PathVariable Long userId){
        List<Orders> ordersList = userService.getOrdersPay(userId);
        return Result.success(ordersList);
    }
}
