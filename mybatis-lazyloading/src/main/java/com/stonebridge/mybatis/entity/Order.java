package com.stonebridge.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private Integer orderId;
    private String orderName;
    private Integer customerId;
    // 声明Customer属性：从多的一端引用一的一端
    private Customer customer;
}
