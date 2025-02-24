package com.atguigu.product.service.impl;
import java.math.BigDecimal;

import com.atguigu.product.bean.Product;
import com.atguigu.product.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(0L);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("苹果-"+productId);
        product.setNum(2);
        return product;
    }
}
