package org.example.elm_spring.mapper;

import org.example.elm_spring.pojo.Business;
import org.example.elm_spring.pojo.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商家数据访问接口（MyBatis Mapper）。
 * 提供对 business、food 和 cart 表的数据库操作方法。
 */
@Mapper
public interface BusinessMapper {

    /**
     * 查询所有商家信息。
     *
     * @return 商家列表
     */
    @Select("select * from business")
    List<Business> list();

    /**
     * 按商家名称模糊搜索商家。
     *
     * @param name 搜索关键词
     * @return 匹配的商家列表
     */
    @Select("select * from business where businessName like concat('%',#{name},'%')")
    List<Business> search(String name);

    /**
     * 按订单分类 ID 查询商家列表。
     *
     * @param orderTypeId 订单分类 ID
     * @return 对应分类下的商家列表
     */
    @Select("select * from business where orderTypeId = #{orderTypeId}")
    List<Business> getByOrderTypeId(Integer orderTypeId);

    /**
     * 按商家 ID 查询单个商家信息。
     *
     * @param businessId 商家 ID
     * @return 对应的商家对象，若不存在则返回 null
     */
    @Select("select * from business where businessId=#{businessId}")
    Business getByBusinessId(Integer businessId);

    /**
     * 新增商家记录。
     *
     * @param business 待新增的商家对象（不含 businessId，由数据库自增）
     */
    @Insert("insert into business" +
            "(businessName, businessAddress, businessExplain, businessImg, orderTypeId, starPrice, deliveryPrice, remarks)" +
            "values " +
            "(#{businessName}, #{businessAddress}, #{businessExplain},#{businessImg},#{orderTypeId},#{starPrice},#{deliveryPrice},#{remarks})")
    void add(Business business);

    /**
     * 按商家 ID 删除商家记录。
     *
     * @param businessId 待删除的商家 ID
     */
    @Delete("delete from business where businessId = #{businessId}")
    void deleteBusiness(Integer businessId);

    /**
     * 按商家 ID 删除该商家下所有食品记录。
     *
     * @param businessId 商家 ID
     */
    @Delete("delete from food where businessId = #{businessId}")
    void deleteFood(Integer businessId);

    /**
     * 查询指定商家下指定用户的购物车条目（仅包含数量不为 0 的记录）。
     *
     * @param businessId 商家 ID
     * @param userId     用户 ID
     * @return 购物车条目列表
     */
    @Select("select * from cart where businessId=#{businessId} and userId = #{userId} and quantity != 0")
    List<Cart> getCartByBusiness(Integer businessId, Long userId);

    /**
     * 按食品 ID 查询该食品的价格。
     *
     * @param foodId 食品 ID
     * @return 食品价格
     */
    @Select("select foodPrice from food where foodId=#{foodId}")
    Double getPrice(Integer foodId);
}
