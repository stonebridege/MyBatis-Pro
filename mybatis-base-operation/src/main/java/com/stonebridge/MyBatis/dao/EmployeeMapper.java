package com.stonebridge.MyBatis.dao;

import com.stonebridge.MyBatis.domain.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 声明这个接口是为了上层代码调用Mybatis的具体功能
 * 接口的全类名要和Mapper配置文件的namespace一直，这样才能通过接口找到Mapper配置
 * 接口中的方法名要和mapper配置中sql语句所在标签的id一致，这样才能通过方法名找到具体的sql语句
 * 又因为mapper配置文件的id属性值时不能重复的，所以当前接口中的方法名也不能重复，当前接口的方法不允许出现重载。
 */
public interface EmployeeMapper {
    /**
     * 通过这个方法对应Mapper配置文件的sql语句
     *
     * @param empId 当前方法的参数对应sql中#{empId}声明的参数
     * @return 当前方法的返回值类型和resultType属性指定的类型一致
     */
    Emp selectEmpById(Long empId);

    Integer insertEmp(Emp emp);

    Integer deleteEmp(Long empId);

    Integer updateEmp(Emp emp);

    Emp selectEmpByName(String name);

    Integer createEmp(@Param("empId") Long empId, @Param("name") String name, @Param("salary") Double salary);

    int updateEmployeeByMap(Map<String, Object> paramMap);

    int selectEmpCount();

    Emp selectEmployee(Integer empId);

    Map<String, Object> selectEmpNameAndMaxSalary();

    List<Emp> selectAll();

    int insertEmployee(Emp employee);

    List<Emp> selectWithResultMap();
}
