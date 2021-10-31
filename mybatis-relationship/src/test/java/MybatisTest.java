import com.stonebridge.mybatis.entity.Customer;
import com.stonebridge.mybatis.entity.Order;
import com.stonebridge.mybatis.mapper.CustomerMapper;
import com.stonebridge.mybatis.mapper.OrderMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MybatisTest {
    private SqlSession session;

    //junit会在每个@Test方法执行前执行
    @Before
    public void init() throws IOException {
        session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
    }

    @Test
    public void testQueryCustomerWithOrderList() {
        CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
        Integer customerId = 1;
        // 执行查询
        Customer customer = customerMapper.selectCustomerWithOrderList(customerId);
        // 打印Customer本身
        System.out.println("customer = " + customer);
        // 从Customer中获取Order集合数据
        List<Order> orderList = customer.getOrderList();
        for (Order order : orderList) {
            System.out.println("order = " + order);
        }
    }

    @Test
    public void testQueryOrderWithCustomer() {
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        Order order = orderMapper.selectOrderWithCustomer(1);
        System.out.println(order);
    }

    @Test
    public void testQueryOrder() {
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        Order order = orderMapper.selectOrderById(1);
        System.out.println(order);
    }


    @After
    public void clear() {
        session.commit();
        session.close();
    }
}
