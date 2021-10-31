package com.stonebridge.mybatis.mapper;

import com.stonebridge.mybatis.entity.Order;

public interface OrderMapper {
    Order selectOrderById(Integer orderId);

    Order selectOrderWithCustomer(Integer orderId);
}
