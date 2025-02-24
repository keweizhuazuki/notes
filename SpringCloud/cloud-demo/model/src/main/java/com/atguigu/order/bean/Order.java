package com.atguigu.order.bean;

import com.atguigu.product.bean.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private Long id;
    private BigDecimal totalAmount;
    private Long userId;
    private String nickName;
    private String Address;
    private List<Product> productList;
}
