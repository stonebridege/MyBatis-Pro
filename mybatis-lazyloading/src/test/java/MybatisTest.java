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
import java.util.concurrent.TimeUnit;

public class MybatisTest {
    private SqlSession session;

    //junit会在每个@Test方法执行前执行
    @Before
    public void init() throws IOException {
        session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
    }

    @Test
    public void testQueryOrderWithOrderList() {
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        Integer orderId = 1;
        // 查询Order对象
        Order order = orderMapper.selectOrderWithOrderTwoStep(orderId);
        // 打印Order对象本身信息
        System.out.println("order = " + order);
        // 通过Order对象获取关联的Customer对象
        Customer customer = order.getCustomer();
        System.out.println("customer = " + customer);
    }

    @Test
    public void testQueryCustomerWithOrderList() throws InterruptedException {
        CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
        Integer customerId = 1;
        // 查询Order对象
        Customer customer = customerMapper.selectCustomerWithOrderList(customerId);
        // 打印Order对象本身信息
//        System.out.println("customer = " + customer);
        System.out.println("customerId:" + customer.getCustomerId());
        System.out.println("customerName:" + customer.getCustomerName());
        TimeUnit.SECONDS.sleep(3);
        // 通过Order对象获取关联的Customer对象
        List<Order> list = customer.getOrderList();
        for (Order order : list) {
            System.out.println("order=" + order);
        }
    }


    @After
    public void clear() {
        session.commit();
        session.close();
    }
}
