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


    @After
    public void clear() {
        session.commit();
        session.close();
    }
}
