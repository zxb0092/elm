package org.example.elm_spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Integer cartId;
    private Integer foodId;
    private Integer businessId;
    private Long userId;
    private Integer quantity;

    private Food food;
}
