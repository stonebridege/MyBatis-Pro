package com.stonebridge.mybatis.mapper;


import com.stonebridge.mybatis.entity.Customer;

public interface CustomerMapper {

    Customer selectCustomerWithOrderList(Integer customerId);
}
