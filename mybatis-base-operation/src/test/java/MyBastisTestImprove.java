import com.stonebridge.myBatis.dao.EmployeeMapper;
import com.stonebridge.myBatis.domain.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MyBastisTestImprove {
    private SqlSession session;

    //junit会在每个@Test方法执行前执行
    @Before
    public void init() throws IOException {
        session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
    }

    @Test
    public void testUserMapperInterface() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        System.out.println("employeeMapper.getClass().getName():" + employeeMapper.getClass().getName());
        //调用employeeMapper的方法去完成对数据库的操作
        Emp emp = employeeMapper.selectEmpById(1L);
        System.out.println(emp.toString());
    }

    @Test
    public void TestInsert() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Integer row = employeeMapper.insertEmp(new Emp(null, "网", 112.333));
        System.out.println(row);
    }

    @Test
    public void TestDelete() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Integer row = employeeMapper.deleteEmp(2l);
        System.out.println(row);
    }

    @Test
    public void TestUpdate() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Integer row = employeeMapper.updateEmp(new Emp(3l, "张三", 222.33));
        System.out.println(row);
    }

    @After
    public void clear() {
        session.commit();
        session.close();
    }

}
