package org.example.elm_spring;

import org.example.elm_spring.mapper.UserMapper;
import org.example.elm_spring.pojo.*;
import org.example.elm_spring.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 测试获取用户信息-正常路径
    @Test
    public void testGetUserInfo_HappyPath() {
        Long userId = 1L;
        User expectedUser = new User();
        expectedUser.setUserId(userId);
        expectedUser.setUserName("testUser");
        when(userMapper.getUserInfo(userId)).thenReturn(expectedUser);

        User actualUser = userService.getUserInfo(userId);

        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }

    // 测试获取用户信息-用户不存在
    @Test
    public void testGetUserInfo_UserNotFound() {
        Long userId = 1L;
        when(userMapper.getUserInfo(userId)).thenReturn(null);

        User actualUser = userService.getUserInfo(userId);

        assertNull(actualUser);
    }

    // 测试登录-正常路径
    @Test
    public void testLogin_HappyPath() {
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("testPassword");
        User expectedUser = new User();
        expectedUser.setUserId(1L);
        expectedUser.setUserName("testUser");
        when(userMapper.login(user)).thenReturn(expectedUser);

        User actualUser = userService.login(user);

        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }

    // 测试登录-用户不存在
    @Test
    public void testLogin_UserNotFound() {
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("testPassword");
        when(userMapper.login(user)).thenReturn(null);

        User actualUser = userService.login(user);

        assertNull(actualUser);
    }

    // 测试注册-正常路径
    @Test
    public void testRegister_HappyPath() {
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("testPassword");
        when(userMapper.getUserByUserName(user.getUserName())).thenReturn(null);


        User actualUser = userService.register(user);

        assertNotNull(actualUser);
        assertEquals(user.getUserName(), actualUser.getUserName());
        assertEquals(user.getPassword(), actualUser.getPassword());
        assertEquals(1, actualUser.getDelTag().longValue());
    }

    // 测试注册-用户名已存在
    @Test
    public void testRegister_UserNameExists() {
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("testPassword");
        User newUser = new User();
        newUser.setUserId(1L);
        newUser.setUserName("testUser");
        when(userMapper.getUserByUserName(user.getUserName())).thenReturn(newUser);

        User actualUser = userService.register(user);

        assertNull(actualUser);
    }

    // 测试获取未支付订单-正常路径
    @Test
    public void testGetOrdersNotPay_HappyPath() {
        Long userId = 1L;
        Orders order = new Orders();
        order.setOrderId(1);
        order.setBusinessId(2);
        OrderDetailet orderDetailet = new OrderDetailet();
        orderDetailet.setOrderId(1);
        orderDetailet.setFoodId(3);
        Food food = new Food();
        food.setFoodId(3);
        food.setFoodName("testFood");
        Business business = new Business();
        business.setBusinessId(2);
        business.setBusinessName("testBusiness");
        List<OrderDetailet> orderDetailetList = new ArrayList<>();
        orderDetailetList.add(orderDetailet);
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(order);

        when(userMapper.getOrdersNotPay(userId)).thenReturn(ordersList);
        when(userMapper.getBusiness(order.getBusinessId())).thenReturn(business);
        when(userMapper.getOrderDetailet(order.getOrderId())).thenReturn(orderDetailetList);
        when(userMapper.getFood(orderDetailet.getFoodId())).thenReturn(food);

        List<Orders> actualOrdersList = userService.getOrdersNotPay(userId);

        assertNotNull(actualOrdersList);
        assertEquals(ordersList, actualOrdersList);
        assertEquals(business, actualOrdersList.get(0).getBusiness());
        assertEquals(food, actualOrdersList.get(0).getOrderDetailetList().get(0).getFood());
    }

    // 测试获取未支付订单-无订单
    @Test
    public void testGetOrdersNotPay_NoOrders() {
        Long userId = 1L;
        List<Orders> ordersList = new ArrayList<>();

        when(userMapper.getOrdersNotPay(userId)).thenReturn(ordersList);

        List<Orders> actualOrdersList = userService.getOrdersNotPay(userId);

        assertNotNull(actualOrdersList);
        assertTrue(actualOrdersList.isEmpty());
    }

    // 测试获取已支付订单-正常路径
    @Test
    public void testGetOrdersPay_HappyPath() {
        Long userId = 1L;
        Orders order = new Orders();
        order.setOrderId(1);
        order.setBusinessId(2);
        OrderDetailet orderDetailet = new OrderDetailet();
        orderDetailet.setOrderId(1);
        orderDetailet.setFoodId(3);
        Food food = new Food();
        food.setFoodId(3);
        food.setFoodName("testFood");
        Business business = new Business();
        business.setBusinessId(2);
        business.setBusinessName("testBusiness");
        List<OrderDetailet> orderDetailetList = new ArrayList<>();
        orderDetailetList.add(orderDetailet);
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(order);

        when(userMapper.getOrdersPay(userId)).thenReturn(ordersList);
        when(userMapper.getBusiness(order.getBusinessId())).thenReturn(business);
        when(userMapper.getOrderDetailet(order.getOrderId())).thenReturn(orderDetailetList);
        when(userMapper.getFood(orderDetailet.getFoodId())).thenReturn(food);

        List<Orders> actualOrdersList = userService.getOrdersPay(userId);

        assertNotNull(actualOrdersList);
        assertEquals(ordersList, actualOrdersList);
        assertEquals(business, actualOrdersList.get(0).getBusiness());
        assertEquals(food, actualOrdersList.get(0).getOrderDetailetList().get(0).getFood());
    }

    // 测试获取已支付订单-无订单
    @Test
    public void testGetOrdersPay_NoOrders() {
        Long userId = 1L;
        List<Orders> ordersList = new ArrayList<>();

        when(userMapper.getOrdersPay(userId)).thenReturn(ordersList);

        List<Orders> actualOrdersList = userService.getOrdersPay(userId);

        assertNotNull(actualOrdersList);
        assertTrue(actualOrdersList.isEmpty());
    }
}
