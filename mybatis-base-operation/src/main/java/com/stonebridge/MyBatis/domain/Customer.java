package com.stonebridge.MyBatis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    private long customerId;
    private String customerName;

    // 体现对多关系
    private List<Order> orderList;
}
