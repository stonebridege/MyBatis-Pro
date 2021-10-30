import com.stonebridge.myBatis.dao.EmployeeMapper;
import com.stonebridge.myBatis.domain.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyBastisTestImprove {
    private SqlSession session;

    //junit会在每个@Test方法执行前执行
    @Before
    public void init() throws IOException {
        session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
    }

    @Test
    public void testDollar() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Emp emp = employeeMapper.selectEmpByName("r");
        System.out.println(emp.toString());
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
    public void TestMultiParama() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Long empId = 24L;
        Double salary = Double.parseDouble("12345.67");
        String name = "stonebridge";
        Integer row = employeeMapper.createEmp(empId, name, salary);
        System.out.println(row);
    }

    @Test
    public void testUpdateEmpNameByMap() {
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("empSalaryKey", 999.99);
        paramMap.put("empIdKey", 3);
        int result = mapper.updateEmployeeByMap(paramMap);
        System.out.println("result = " + result);
    }

    @Test
    public void TestUpdate() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Integer row = employeeMapper.updateEmp(new Emp(3l, "张三", 222.33));
        System.out.println(row);
    }

    @Test
    public void testEmpCount() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        int count = employeeMapper.selectEmpCount();
        System.out.println("count = " + count);
    }

    @Test
    public void testSelectEmployee() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Emp emp = employeeMapper.selectEmployee(23);
        System.out.println(emp);
    }

    @Test
    public void testQueryEmpNameAndSalary() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Map<String, Object> resultMap = employeeMapper.selectEmpNameAndMaxSalary();
        Set<Map.Entry<String, Object>> entrySet = resultMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + "=" + value);
        }
    }

    @Test
    public void testSelectAll() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        List<Emp> employeeList = employeeMapper.selectAll();
        for (Emp employee : employeeList) {
            System.out.println("employee = " + employee);
        }
    }

    @Test
    public void testSaveEmp() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Emp employee = new Emp();
        employee.setEmpName("john");
        employee.setEmpSalary(666.66);
        employeeMapper.insertEmployee(employee);
        System.out.println("employee.getEmpId() = " + employee.getEmpId());
    }

    @After
    public void clear() {
        session.commit();
        session.close();
    }
}
