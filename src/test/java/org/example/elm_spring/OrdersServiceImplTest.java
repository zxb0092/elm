package org.example.elm_spring;

import org.example.elm_spring.mapper.CartMapper;
import org.example.elm_spring.mapper.OrdersMapper;
import org.example.elm_spring.pojo.*;
import org.example.elm_spring.service.OrdersService;
import org.example.elm_spring.service.impl.OrdersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrdersServiceImplTest {

    @Mock
    private OrdersMapper ordersMapper;

    @Mock
    private CartMapper cartMapper;

    @InjectMocks
    private OrdersServiceImpl ordersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOrders_NormalCase() {
        // Mock data
        Orders orders = new Orders();
        orders.setBusinessId(1);

        orders.setOrderState(0);

        List<Cart> cartList = Arrays.asList(

        );

        when(ordersMapper.check(orders)).thenReturn(0);
        when(ordersMapper.getCarts(1, 1L)).thenReturn(cartList);

        // Execute test
        Integer orderId = ordersService.addOrders(orders);

        // Verify result
        assertNotNull(orderId);
        assertTrue(orderId >= 0);

        // Verify calls
        verify(ordersMapper).check(orders);
        verify(ordersMapper).addOrders(orders);

        verify(ordersMapper, times(2)).addOrderDetailet(any(OrderDetailet.class));
        verify(ordersMapper, times(2)).updateCartQuantity(anyInt());
    }

    @Test
    void testAddOrders_OrderExists() {
        // Mock data
        Orders orders = new Orders();
        when(ordersMapper.check(orders)).thenReturn(1);

        // Execute test
        Integer orderId = ordersService.addOrders(orders);

        // Verify result
        assertEquals(-1, orderId);

        // Verify calls
        verify(ordersMapper).check(orders);
        verify(ordersMapper, never()).addOrders(orders);

    }

    @Test
    void testAddOrders_CartIsEmpty() {
        // Mock data
        Orders orders = new Orders();
        orders.setBusinessId(1);


        when(ordersMapper.check(orders)).thenReturn(0);
        when(ordersMapper.getCarts(1, 1L)).thenReturn(Arrays.asList());

        // Execute test
        Integer orderId = ordersService.addOrders(orders);

        // Verify result
        assertNotNull(orderId);
        assertTrue(orderId >= 0);

        // Verify calls
        verify(ordersMapper).check(orders);
        verify(ordersMapper).addOrders(orders);
        verify(ordersMapper, never()).addOrderDetailet(any(OrderDetailet.class));
        verify(ordersMapper, never()).updateCartQuantity(anyInt());
    }

    @Test
    void testGetOrderDetailets_NormalCase() {
        // Mock data
        Integer orderId = 1;
        List<OrderDetailet> orderDetailetList = Arrays.asList(

        );

        when(ordersMapper.getOrderDetailets(orderId)).thenReturn(orderDetailetList);


        // Execute test
        List<OrderDetailet> result = ordersService.getOrderDetailets(orderId);

        // Verify result
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("美食1", result.get(0).getFood().getFoodName());
        assertEquals("美食2", result.get(1).getFood().getFoodName());

        // Verify calls
        verify(ordersMapper).getOrderDetailets(orderId);
        verify(ordersMapper).getFoodByFoodId(1);
        verify(ordersMapper).getFoodByFoodId(2);
    }

    @Test
    void testGetOrderDetailets_DetailsIsEmpty() {
        // Mock data
        Integer orderId = 1;
        when(ordersMapper.getOrderDetailets(orderId)).thenReturn(Arrays.asList());

        // Execute test
        List<OrderDetailet> result = ordersService.getOrderDetailets(orderId);

        // Verify result
        assertNotNull(result);
        assertEquals(0, result.size());

        // Verify calls
        verify(ordersMapper).getOrderDetailets(orderId);
        verify(ordersMapper, never()).getFoodByFoodId(anyInt());
    }

    @Test
    void testGetBusinessByOrderId_NormalCase() {
        // Mock data
        Integer orderId = 1;

        when(ordersMapper.getBusinessId(orderId)).thenReturn(1);


        // Execute test
        Business result = ordersService.getBusinessByOrderId(orderId);

        // Verify result
        assertNotNull(result);
        assertEquals("商家1", result.getBusinessName());

        // Verify calls
        verify(ordersMapper).getBusinessId(orderId);
        verify(ordersMapper).getBusiness(1);
    }

    @Test
    void testGetBusinessByOrderId_OrderDoesNotExist() {
        // Mock data
        Integer orderId = 1;
        when(ordersMapper.getBusinessId(orderId)).thenReturn(null);

        // Execute test
        Business result = ordersService.getBusinessByOrderId(orderId);

        // Verify result
        assertNull(result);

        // Verify calls
        verify(ordersMapper).getBusinessId(orderId);
        verify(ordersMapper, never()).getBusiness(anyInt());
    }

    @Test
    void testGetOrderByOrderId_NormalCase() {
        // Mock data
        Integer orderId = 1;



        // Execute test
        Orders result = ordersService.getOrderByOrderId(orderId);

        // Verify result
        assertNotNull(result);
        assertEquals("地址1", result.getDeliveryAddress().getAddress());

        // Verify calls
        verify(ordersMapper).getOrderByOrderId(orderId);
        verify(ordersMapper).getAddressByDaId(1);
    }

    @Test
    void testGetOrderByOrderId_OrderDoesNotExist() {
        // Mock data
        Integer orderId = 1;
        when(ordersMapper.getOrderByOrderId(orderId)).thenReturn(null);

        // Execute test
        Orders result = ordersService.getOrderByOrderId(orderId);

        // Verify result
        assertNull(result);

        // Verify calls
        verify(ordersMapper).getOrderByOrderId(orderId);
        verify(ordersMapper, never()).getAddressByDaId(anyInt());
    }



    @Test
    void testDeleteOrders_NormalCase() {
        // Mock data
        Integer orderId = 1;

        // Execute test
        ordersService.deleteOrders(orderId);

        // Verify calls
        verify(ordersMapper).deleteOrders(orderId);
        verify(ordersMapper).deleteOrderDetails(orderId);
    }
}
