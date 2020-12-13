package com.pshdhx.cache.mapper;

import com.pshdhx.cache.domain.Employee;
import org.apache.ibatis.annotations.*;

/**
 * @Authtor pshdhx
 * @Date 2020/12/1021:13
 * @Version 1.0
 */
@Mapper
public interface Employeemapper {

    @Select("select * from employee where id = #{id}")
    public Employee getEmployee(Integer Id);

    @Update("update employee set last_name = #{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id = #{id}")
    public void updateEmployee(Employee employee);

    @Delete("delete from employee where id=#{id}")
    public void deleteEmp(Integer id);

    @Insert("insert into employee(last_name,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);

    @Select("select * from employee where last_name=#{lastName}")
    public  Employee getEmployee(String lastName);
}
