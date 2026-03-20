package org.example.elm_spring.mapper;

import org.example.elm_spring.pojo.Cart;
import org.example.elm_spring.pojo.Food;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 食品数据访问接口（MyBatis Mapper）。
 * 提供对 food 表和 cart 表中与食品相关数据的查询与操作方法。
 */
@Mapper
public interface FoodMapper {

    /**
     * 查询指定商家下的所有食品列表（不关联用户购物车数量）。
     *
     * @param businessId 商家 ID
     * @return 食品列表
     */
    @Select("select * from food where businessId=#{businessId}")
    List<Food> listFoodByBusiness(Integer businessId);

    /**
     * 查询指定商家下的所有食品列表（用于后续关联当前用户购物车数量）。
     *
     * @param businessId 商家 ID
     * @param userId     用户 ID（仅作为占位参数，不参与 SQL 过滤，由上层 Service 层负责关联购物车数据）
     * @return 食品列表
     */
    @Select("select * from food where businessId=#{businessId}")
    List<Food> listFood(Integer businessId, Long userId);

    /**
     * 新增食品记录。
     *
     * @param food 待新增的食品对象（不含 foodId，由数据库自增）
     */
    @Insert("insert into food(foodName, foodExplain, foodImg, foodPrice, businessId, remarks)" +
            "values (#{foodName}, #{foodExplain}, #{foodImg}, #{foodPrice}, #{businessId}, #{remarks})")
    void addFood(Food food);

    /**
     * 查询某用户对某食品的购物车条目（包含 cartId、数量等信息）。
     *
     * @param foodId 食品 ID
     * @param userId 用户 ID
     * @return 对应的购物车条目，若不存在则返回 null
     */
    @Select("select * from cart where foodId=#{foodId} and userId = #{userId}")
    Cart getCard(Integer foodId, Long userId);

    /**
     * 查询某用户对某食品在购物车中的数量。
     *
     * @param foodId 食品 ID
     * @param userId 用户 ID
     * @return 购物车中该食品的数量，若不存在则返回 null
     */
    @Select("select quantity from cart where foodId=#{foodId} and userId = #{userId}")
    Integer getQuantity(Integer foodId, Long userId);

    /**
     * 查询某用户对某食品在购物车中的条目 ID。
     *
     * @param foodId 食品 ID
     * @param userId 用户 ID
     * @return 购物车条目 ID，若不存在则返回 null
     */
    @Select("select cartId from cart where foodId=#{foodId} and userId = #{userId}")
    Integer getCartId(Integer foodId, Long userId);

    /**
     * 按食品 ID 删除食品记录。
     *
     * @param foodId 待删除的食品 ID
     */
    @Delete("delete from food where foodId=#{foodId}")
    void deleteFood(Integer foodId);
}
