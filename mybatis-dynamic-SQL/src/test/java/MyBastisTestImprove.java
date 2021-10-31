import com.stonebridge.MyBatis.dao.EmployeeMapper;
import com.stonebridge.MyBatis.domain.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBastisTestImprove {
    private SqlSession session;

    //junit会在每个@Test方法执行前执行
    @Before
    public void init() throws IOException {
        session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
    }

    @Test
    public void testQueryEmp() {
        EmployeeMapper customerMapper = session.getMapper(EmployeeMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("empName", "stonebridge");
        map.put("empSalary", 200000);
        List<Emp> list = customerMapper.selectEmpByCondition(map);
        for (Emp emp : list) {
            System.out.println("emp:" + emp.toString());
        }
    }

    @Test
    public void testUpadteEmpCondition() {
        EmployeeMapper customerMapper = session.getMapper(EmployeeMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("empName", "stonebridge");
        map.put("empSalary", 20000);
        Integer row = customerMapper.updateEmpConditional(map);
    }

    @Test
    public void testBatchInsert() {
        EmployeeMapper customerMapper = session.getMapper(EmployeeMapper.class);
        List<Emp> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Emp emp = new Emp(null, "tiger_" + i, i * 1000.00);
            list.add(emp);
        }
        customerMapper.batchInsert(list);
    }

    @Test
    public void testSelectEmpByConditionByTrim() {
        EmployeeMapper customerMapper = session.getMapper(EmployeeMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("empName", "stonebridge");
//        map.put("empSalary", 2000);
        List<Emp> list = customerMapper.selectEmpByConditionByTrim(map);
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }


    @After
    public void clear() {
        session.commit();
        session.close();
    }
}
