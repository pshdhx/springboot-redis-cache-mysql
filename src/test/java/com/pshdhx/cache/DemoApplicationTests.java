package com.pshdhx.cache;

import com.pshdhx.cache.domain.Employee;
import com.pshdhx.cache.mapper.Employeemapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {


    @Autowired
    Employeemapper employeemapper;

    @Test
    public void contextLoads(){
        Employee employee = employeemapper.getEmployee(1);
        System.out.println(employee.toString());
    }

}
