package com.stonebridge.MyBatis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private long orderId;
    private String orderName;
    private long customerId;
    // 体现对一关系
    private Customer customer;
}
