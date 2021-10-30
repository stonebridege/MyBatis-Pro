package com.stonebridge.myBatis.dao;

import com.stonebridge.myBatis.domain.Order;

public interface OrderMapper {
    Order selectOrderWithCustomer(Integer orderId);
}
