import com.stonebridge.MyBatis.dao.EmployeeMapper;
import com.stonebridge.MyBatis.domain.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MyBastisTestImprove {
    private SqlSessionFactory factory;

    //junit会在每个@Test方法执行前执行
    @Before
    public void init() throws IOException {
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
    }

    @Test
    public void testFirstLevelCache() {
        SqlSession session = factory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Integer empId = 1;
        Emp emp01 = mapper.selectEmpById(empId);
        System.out.println("第一次查询emp01=" + emp01.toString());

        Emp emp02 = mapper.selectEmpById(empId);
        System.out.println("第二次查询emp02=" + emp02.toString());
        session.commit();
        session.close();
    }

    @Test
    public void testFirstLevelCacheNotWork01() {
        SqlSession session = factory.openSession();
        SqlSession session1 = factory.openSession();
        EmployeeMapper mapper1 = session.getMapper(EmployeeMapper.class);
        Integer empId = 1;
        Emp emp01 = mapper1.selectEmpById(empId);
        System.out.println("第一次查询emp01=" + emp01.toString());
        EmployeeMapper mapper2 = session1.getMapper(EmployeeMapper.class);
        Emp emp02 = mapper2.selectEmpById(empId);
        System.out.println("第二次查询emp02=" + emp02.toString());
        session.commit();
        session.close();
        session1.commit();
        session1.close();
    }

    @Test
    public void testFirstLevelCacheNotWork02() {
        // 一级缓存失效情况二：查询条件不同
        SqlSession session = factory.openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Integer empId = 1;
        // 第一次查询：需要查询数据库
        Emp emp01 = employeeMapper.selectEmpById(empId);
        System.out.println("emp01 = " + emp01);
        // 第二次查询：查询条件不同，无法使用已缓存数据
        empId = 2;
        Emp emp02 = employeeMapper.selectEmpById(empId);
        System.out.println("emp02 = " + emp02);
        session.commit();
        session.close();
    }

    @Test
    public void testFirstLevelCacheNotWork03() {
        // 一级缓存失效情况三：两次查询之间执行了增删改操作
        SqlSession session = factory.openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Integer empId = 1;
        // 第一次查询：需要查询数据库
        Emp emp01 = employeeMapper.selectEmpById(empId);
        System.out.println("emp01 = " + emp01);
        // 增删改操作
        employeeMapper.updateEmp(new Emp(4L, "pott", 9999.9));
        // 第二次查询：没有发送SQL语句到数据库，证明数据是从缓存中获取到的
        Emp emp02 = employeeMapper.selectEmpById(empId);
        System.out.println("emp02 = " + emp02);
        session.commit();
        session.close();
    }

    @Test
    public void testFirstLevelCacheNotWork04() {
        // 一级缓存失效情况四：两次查询之间手动清空缓存
        SqlSession session = factory.openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Integer empId = 1;
        // 第一次查询：需要查询数据库
        Emp emp01 = employeeMapper.selectEmpById(empId);
        System.out.println("emp01 = " + emp01);
        // 清空缓存
        session.clearCache();
        // 第二次查询：没有发送SQL语句到数据库，证明数据是从缓存中获取到的
        Emp emp02 = employeeMapper.selectEmpById(empId);
        System.out.println("emp02 = " + emp02);
        session.commit();
        session.close();
    }

    @Test
    public void testFirstLevelCacheNotWork05() {
        // 一级缓存失效情况五：两次查询之间提交事务
        SqlSession session = factory.openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Integer empId = 1;
        // 第一次查询：需要查询数据库
        Emp emp01 = employeeMapper.selectEmpById(empId);
        System.out.println("emp01 = " + emp01);
        // 提交事务
        session.commit();
        // 第二次查询：没有发送SQL语句到数据库，证明数据是从缓存中获取到的
        Emp emp02 = employeeMapper.selectEmpById(empId);
        System.out.println("emp02 = " + emp02);
        session.commit();
        session.close();
    }

    @Test
    public void testSecondLevelCache() {
        // 测试二级缓存存在：使用两个不同SqlSession执行查询
        // 说明：SqlSession提交事务时才会将查询到的数据存入二级缓存
        // 所以本例并没有能够成功从二级缓存获取到数据
        SqlSession session01 = factory.openSession();
        SqlSession session02 = factory.openSession();
        EmployeeMapper mapper01 = session01.getMapper(EmployeeMapper.class);
        EmployeeMapper mapper02 = session02.getMapper(EmployeeMapper.class);
        Integer empId = 1;
        // DEBUG 11-01 16:59:15,933 Cache Hit Ratio [com.stonebridge.MyBatis.dao.EmployeeMapper]: 0.0
        Emp emp01 = mapper01.selectEmpById(empId);
        // DEBUG 11-01 16:59:16,630 Cache Hit Ratio [com.stonebridge.MyBatis.dao.EmployeeMapper]: 0.0
        Emp emp02 = mapper02.selectEmpById(empId);
        System.out.println("emp01 = " + emp01);
        System.out.println("emp02 = " + emp02);
        session01.commit();
        session01.close();
        session02.commit();
        session02.close();
    }

    @Test
    public void testSecondLevelCacheWork() {
        SqlSession session = factory.openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Integer empId = 1;
        // 第一次查询
        Emp emp01 = employeeMapper.selectEmpById(empId);
        System.out.println("emp01 = " + emp01);
        // 提交事务
        session.commit();
        // 关闭旧SqlSession
        session.close();
        // 开启新SqlSession
        session = factory.openSession();
        // 第二次查询
        employeeMapper = session.getMapper(EmployeeMapper.class);
        Emp emp02 = employeeMapper.selectEmpById(empId);
        System.out.println("emp02 = " + emp02);
        session.commit();
        session.close();
        session = factory.openSession();
        employeeMapper = session.getMapper(EmployeeMapper.class);
        employeeMapper.selectEmpById(empId);
        session.commit();
        session.close();
    }
}
