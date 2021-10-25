import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Mybatis {
    @Test
    public void testHelloWorldReview() throws IOException {
        //1.借助Mybatis的Resource类将Mybatis全局配置文件读取到内存中
        //这里使用的路径仍然是一个以类路径目录为基准的相对路径
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //2.创建SqlSessionFactoryBuilder对象。SqlSessionFactoryBuilder对象主要用于创建SqlSessionFactory，SqlSessionFactory又是生产SqlSession的
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.调用builder对象的build()方法读取《全局配置文件的输入流》来创建SqlSessionFactory
        SqlSessionFactory factory = builder.build(inputStream);
        //4.调用工厂对象的方法开启一个会话，从java程序的持久层到数据库的会话
        SqlSession session = factory.openSession();
        //5.执行Mapper配置文件中准备好sql语句。找到sql语句的过程：通过namespace找到mapper映射文件的sql语句标签
        //备注：此时执行的查找操作的sql已经不是到xml配置文件中找了，因为xml配置文件中的信息已经读取到内存中封装成了对象，
        //所以此时其实是到已经封装的对象中查找，查找的依据是：mapper配置文件namespace值，sql语句标签的id
        String statement = "com.stonebridge.myBatis.dao.EmployeeMapper.selectEmpById";
        Long empId = 1L;
        Object object = session.selectOne(statement, empId);
        // 6.直接打印查询结果
        System.out.println("object = " + object);
        // 7.提交事务
        session.commit();
        // 8.关闭SqlSession
        session.close();
    }

    @Test
    public void TestSelect() throws IOException {
        // 1.使用Mybatis的Resources类读取Mybatis全局配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 3.调用builder对象的build方法创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = builder.build(inputStream);
        // 4.通过SqlSessionFactory对象开启一个从Java程序到数据库的会话
        SqlSession session = sessionFactory.openSession();
        // 5.通过SqlSession对象找到Mapper配置文件中可以执行的SQL语句
        // statement参数的格式：Mapper配置文件的namespace属性.SQL标签的id属性
        // parameter参数：给SQL语句传入的参数
        Object object = session.selectOne("com.stonebridge.myBatis.dao.EmployeeMapper.selectEmpById", "1");
        // 6.直接打印查询结果
        System.out.println("object = " + object);
        // 7.提交事务
        session.commit();
        // 8.关闭SqlSession
        session.close();
    }
}
