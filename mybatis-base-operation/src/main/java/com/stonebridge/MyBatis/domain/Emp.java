package com.stonebridge.MyBatis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 和数据库表t_emp对应的实体类
 * emp_id INT AUTO_INCREMENT
 * emp_name CHAR(100)
 * emp_salary DOUBLE(10,5)
 * <p>
 * Java的实体类中，属性的类型不要使用基本数据类型，要使用包装类型。因为包装类型可以赋值为null，表示空，而基本数据类型不可以。
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Emp {
    private Long empId;
    private String empName;
    private Double empSalary;
}
