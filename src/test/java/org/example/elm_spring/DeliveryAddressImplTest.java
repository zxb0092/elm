package org.example.elm_spring;

import org.example.elm_spring.mapper.DeliveryAddressMapper;
import org.example.elm_spring.pojo.DeliveryAddress;
import org.example.elm_spring.service.impl.DeliveryAddressImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeliveryAddressImplTest {

    @InjectMocks
    private DeliveryAddressImpl deliveryAddressService;

    @Mock
    private DeliveryAddressMapper deliveryAddressMapper;

    private DeliveryAddress address1;
    private DeliveryAddress address2;

    @BeforeEach
    public void setUp() {
        address1 = new DeliveryAddress();
        address1.setDaId(1);
        address1.setUserId(101L);
        address1.setAddress("北京市朝阳区");

        address2 = new DeliveryAddress();
        address2.setDaId(2);
        address2.setUserId(102L);
        address2.setAddress("上海市浦东新区");
    }

    // 测试获取用户的所有配送地址 - 正常情况
    @Test
    public void testList_HappyPath() {
        when(deliveryAddressMapper.list(101L)).thenReturn(Arrays.asList(address1));
        List<DeliveryAddress> addresses = deliveryAddressService.list(101L);
        assertFalse(addresses.isEmpty());
        assertEquals(1, addresses.size());
        assertEquals("北京市朝阳区", addresses.get(0).getAddress());
    }

    // 测试获取用户的所有配送地址 - 用户无地址情况
    @Test
    public void testList_NoAddresses() {
        when(deliveryAddressMapper.list(103L)).thenReturn(new ArrayList<>());
        List<DeliveryAddress> addresses = deliveryAddressService.list(103L);
        assertTrue(addresses.isEmpty());
    }

    // 测试添加配送地址 - 正常情况
    @Test
    public void testAddAddress_HappyPath() {
        deliveryAddressService.addAddress(address1);
        verify(deliveryAddressMapper, times(1)).addAddress(address1);
    }

    // 测试添加配送地址 - 地址为空情况
    @Test
    public void testAddAddress_NullAddress() {
        assertThrows(NullPointerException.class, () -> deliveryAddressService.addAddress(null));
    }

    // 测试删除配送地址 - 正常情况
    @Test
    public void testDeleteAddress_HappyPath() {
        deliveryAddressService.deleteAddress(1);
        verify(deliveryAddressMapper, times(1)).deleteAddress(1);
    }

    // 测试删除配送地址 - 地址ID为空情况
    @Test
    public void testDeleteAddress_NullId() {
        assertThrows(NullPointerException.class, () -> deliveryAddressService.deleteAddress(null));
    }

    // 测试更新配送地址 - 正常情况
    @Test
    public void testUpdateAddress_HappyPath() {
        deliveryAddressService.updateAddress(address1);
        verify(deliveryAddressMapper, times(1)).updateAddress(address1);
    }

    // 测试更新配送地址 - 地址为空情况
    @Test
    public void testUpdateAddress_NullAddress() {
        assertThrows(NullPointerException.class, () -> deliveryAddressService.updateAddress(null));
    }

    // 测试根据地址ID获取配送地址 - 正常情况
    @Test
    public void testGetByDaId_HappyPath() {
        when(deliveryAddressMapper.getByDaId(1)).thenReturn(address1);
        DeliveryAddress address = deliveryAddressService.getByDaId(1);
        assertNotNull(address);
        assertEquals("北京市朝阳区", address.getAddress());
    }

    // 测试根据地址ID获取配送地址 - 地址ID为空情况
    @Test
    public void testGetByDaId_NullId() {
        assertThrows(NullPointerException.class, () -> deliveryAddressService.getByDaId(null));
    }

    // 测试根据地址ID获取配送地址 - 地址不存在情况
    @Test
    public void testGetByDaId_AddressNotFound() {
        when(deliveryAddressMapper.getByDaId(3)).thenReturn(null);
        DeliveryAddress address = deliveryAddressService.getByDaId(3);
        assertNull(address);
    }
}
