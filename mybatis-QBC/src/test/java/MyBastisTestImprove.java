import com.atguigu.mybatis.entity.Employee;
import com.atguigu.mybatis.entity.EmployeeExample;
import com.atguigu.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MyBastisTestImprove {
    private SqlSession session;


    @Test
    public void testQBC() {
        // 目标：组装查询条件WHERE (xxx and xxx) or (xxx and xxx)
        // 1.创建Example对象
        EmployeeExample example = new EmployeeExample();

        // 2.根据Example对象创建Criteria对象
        EmployeeExample.Criteria criteria01 = example.createCriteria();

        EmployeeExample.Criteria criteria02 = example.or();

        // 3.在Criteria对象中添加查询条件
        // ①emp_name like %o% and emp_salary > 6000.00
        criteria01
                .andEmpNameLike("%o%")
                .andEmpSalaryGreaterThan(6000.00);

        // ②emp_name like %t% and emp_salary < 3000.00
        criteria02
                .andEmpNameLike("%t%")
                .andEmpSalaryLessThan(3000.00);

        // 4.根据Example对象执行查询
        EmployeeMapper employeeMapper = session.
                getMapper(EmployeeMapper.class);

        List<Employee> employeeList = employeeMapper.selectByExample(example);

        for (Employee employee : employeeList) {
            System.out.println("employee = " + employee);
        }

        // 最终运行的结果：WHERE ( emp_name like ? and emp_salary > ? ) or( emp_name like ? and emp_salary < ? )
    }

    @Before
    public void init() throws IOException {
        session =
                new SqlSessionFactoryBuilder()
                        .build(Resources.getResourceAsStream("mybatis-config.xml"))
                        .openSession();
    }

    @After
    public void clear() {
        session.commit();
        session.close();
    }
}
