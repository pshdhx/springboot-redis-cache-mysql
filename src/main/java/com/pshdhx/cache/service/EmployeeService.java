package com.pshdhx.cache.service;

import com.pshdhx.cache.domain.Employee;
import com.pshdhx.cache.mapper.Employeemapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @Authtor pshdhx
 * @Date 2020/12/1317:48
 * @Version 1.0
 */
@Service
public class EmployeeService {
    @Autowired
    private Employeemapper employeemapper;

    /**
     * 将方法的结果进行缓存，以后要是在遇到相同的数据，直接从缓存中获取即可，不用调用查询数据库的方法
     * @param id
     * @return
     */
//    @Cacheable(cacheNames = "emp",keyGenerator = "myKeyGenerator",condition = "#a0>1 and #root.methodName eq 'aaa'",unless = "#a0==2")
    @Cacheable(cacheNames = "emp")
    public Employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工");
        Employee emp = employeemapper.getEmployee(id);
        return emp;
    }


    @CachePut(value = "emp",key = "#result.id")
    public Employee updateEmp(Employee employee){
        employeemapper.updateEmployee(employee);
        return employee;
    }

    /**
     * key是清除指定缓存，
     * allEntries是清除所有缓存,allEntries = true
     * @param id
     */
    @CacheEvict(value = "emp",key = "#id")
    public void deleteEmp(Integer id){
        System.out.println("deleteEmp:"+id);
    }

    @Caching(
            cacheable={
             @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
             @CachePut(value = "emp",key = "#result.id"),
             @CachePut(value = "emp",key = "#result.email")
            }
    )//Key选择了lastname作key，但是put中使用了result.id作为key缓存，所有传递id也能从缓存中取到数据；
    public Employee getEmployeeByLastName(String lastName){
        return employeemapper.getEmployee(lastName);
    }
}
