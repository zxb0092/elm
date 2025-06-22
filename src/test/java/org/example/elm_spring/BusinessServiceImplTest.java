package org.example.elm_spring;

import org.example.elm_spring.mapper.BusinessMapper;
import org.example.elm_spring.pojo.Business;
import org.example.elm_spring.pojo.Cart;
import org.example.elm_spring.service.BusinessService;
import org.example.elm_spring.service.impl.BusinessServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BusinessServiceImplTest {

    @Mock
    private BusinessMapper mapper;

    @InjectMocks
    private BusinessServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testList_NormalCase() {
        List<Business> businesses = new ArrayList<>();
        businesses.add(new Business());
        when(mapper.list()).thenReturn(businesses);

        List<Business> result = service.list();
        assertEquals(1, result.size());
    }

    @Test
    public void testSearch_NormalCase() {
        String name = "testBusiness";
        List<Business> businesses = new ArrayList<>();
        businesses.add(new Business());
        when(mapper.search(name)).thenReturn(businesses);

        List<Business> result = service.search(name);
        assertEquals(1, result.size());
    }

    @Test
    public void testSearch_EmptyString() {
        String name = "";
        List<Business> businesses = new ArrayList<>();
        when(mapper.search(name)).thenReturn(businesses);

        List<Business> result = service.search(name);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearch_EmptyResult() {
        String name = "nonExistentBusiness";
        when(mapper.search(name)).thenReturn(new ArrayList<>());

        List<Business> result = service.search(name);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetByOrderTypeId_NormalCase() {
        Integer orderTypeId = 1;
        List<Business> businesses = new ArrayList<>();
        businesses.add(new Business());
        when(mapper.getByOrderTypeId(orderTypeId)).thenReturn(businesses);

        List<Business> result = service.getByOrderTypeId(orderTypeId);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetByOrderTypeId_EmptyResult() {
        Integer orderTypeId = 999;
        when(mapper.getByOrderTypeId(orderTypeId)).thenReturn(new ArrayList<>());

        List<Business> result = service.getByOrderTypeId(orderTypeId);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAdd_NormalCase() {
        Business business = new Business();
        service.add(business);

        verify(mapper, times(1)).add(business);
    }

    @Test
    public void testDelete_NormalCase() {
        Integer businessId = 1;
        service.delete(businessId);

        verify(mapper, times(1)).deleteBusiness(businessId);
        verify(mapper, times(1)).deleteFood(businessId);
    }

    @Test
    public void testGetByBusinessId_NormalCase() {
        Integer businessId = 1;
        Business business = new Business();
        business.setBusinessId(businessId);
        when(mapper.getByBusinessId(businessId)).thenReturn(business);

        Business result = service.getByBusinessId(businessId);
        assertNotNull(result);
        assertEquals(businessId, result.getBusinessId());
    }

    @Test
    public void testGetByBusinessId_EmptyResult() {
        Integer businessId = 999;
        when(mapper.getByBusinessId(businessId)).thenReturn(null);

        Business result = service.getByBusinessId(businessId);
        assertNull(result);
    }

    @Test
    public void testTotal_NormalCase() {
        Integer businessId = 1;
        Long userId = 1L;
        Business business = new Business();
        business.setDeliveryPrice(5.0);
        List<Cart> cartList = new ArrayList<>();
        when(mapper.getByBusinessId(businessId)).thenReturn(business);
        when(mapper.getCartByBusiness(businessId, userId)).thenReturn(cartList);
        when(mapper.getPrice(1)).thenReturn(10.0);

        Double result = service.total(businessId, userId);
        assertEquals(25.0, result); // 10.0 * 2 + 5.0
    }

    @Test
    public void testTotal_EmptyCart() {
        Integer businessId = 1;
        Long userId = 1L;
        Business business = new Business();
        business.setDeliveryPrice(5.0);
        List<Cart> cartList = new ArrayList<>();
        when(mapper.getByBusinessId(businessId)).thenReturn(business);
        when(mapper.getCartByBusiness(businessId, userId)).thenReturn(cartList);

        Double result = service.total(businessId, userId);
        assertEquals(5.0, result); // Only delivery fee
    }

    @Test
    public void testTotal_EmptyResult() {
        Integer businessId = 999;
        Long userId = 1L;
        when(mapper.getByBusinessId(businessId)).thenReturn(null);
        when(mapper.getCartByBusiness(businessId, userId)).thenReturn(new ArrayList<>());

        Double result = service.total(businessId, userId);
        assertNull(result);
    }

    @Test
    public void testGetQuantity_NormalCase() {
        Integer businessId = 1;
        Long userId = 1L;
        List<Cart> cartList = new ArrayList<>();
        when(mapper.getCartByBusiness(businessId, userId)).thenReturn(cartList);

        Integer result = service.getQuantity(businessId, userId);
        assertEquals(5, result); // 2 + 3
    }

    @Test
    public void testGetQuantity_EmptyCart() {
        Integer businessId = 1;
        Long userId = 1L;
        List<Cart> cartList = new ArrayList<>();
        when(mapper.getCartByBusiness(businessId, userId)).thenReturn(cartList);

        Integer result = service.getQuantity(businessId, userId);
        assertEquals(0, result);
    }

    @Test
    public void testGetQuantity_EmptyResult() {
        Integer businessId = 999;
        Long userId = 1L;
        when(mapper.getCartByBusiness(businessId, userId)).thenReturn(null);

        Integer result = service.getQuantity(businessId, userId);
        assertNull(result);
    }
}
