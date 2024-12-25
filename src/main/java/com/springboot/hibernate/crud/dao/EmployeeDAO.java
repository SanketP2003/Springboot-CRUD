package com.springboot.hibernate.crud.dao;

import com.springboot.hibernate.crud.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    void save(Employee theEmployee);

    Employee findByEmployeeId(Integer empid);

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByLastname(String lastname);

    List<Employee> findByEmail(String emailid);

    List<Employee> findAll();

    void update(Employee theEmployee);

    void delete(Integer empid);

    int deleteAll();
}
