package com.stonebridge.MyBatis.dao;

import com.stonebridge.MyBatis.domain.Order;

public interface OrderMapper {
    Order selectOrderWithCustomer(Integer orderId);
}
