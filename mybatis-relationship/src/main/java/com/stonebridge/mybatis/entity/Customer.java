package com.stonebridge.mybatis.entity;

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
    private Integer customerId;
    private String customerName;
    // 声明order的List集合类型的属性，建立『对多(对方是多的一端)』关联关系
    private List<Order> orderList;
}
