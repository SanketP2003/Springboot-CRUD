package com.springboot.hibernate.crud.dao;

import com.springboot.hibernate.crud.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Employee theEmployee) {
        entityManager.persist(theEmployee);
    }

    @Override
    public Employee findByEmployeeId(Integer empid) {
        return entityManager.find(Employee.class, empid);
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("From Employee order by lastname", Employee.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Employee> findByFirstName(String firstname) {

        TypedQuery<Employee> theQuery = entityManager.createQuery("From Employee where firstname = :theData", Employee.class);
        theQuery.setParameter("theData", firstname);
        return theQuery.getResultList();
    }

    @Override
    public List<Employee> findByLastname(String lastname) {

        TypedQuery<Employee> theQuery = entityManager.createQuery("From Employee where lastname = :theData", Employee.class);
        theQuery.setParameter("theData", lastname);
        return theQuery.getResultList();
    }

    @Override
    public List<Employee> findByEmail(String email) {

        TypedQuery<Employee> theQuery = entityManager.createQuery("From Employee where emailid = :theData", Employee.class);
        theQuery.setParameter("theData", email);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Employee theEmployee) {
        entityManager.merge(theEmployee);
    }

    @Override
    @Transactional
    public void delete(Integer empid) {
        //retrieve the employee
        Employee theEmployee = entityManager.find(Employee.class, empid);
        //delete the employee
        entityManager.remove(theEmployee);
    }

    @Override
    @Transactional
    public int deleteAll() {

        int numRowsDeleted = entityManager.createQuery("DELETE FROM Employee").executeUpdate();
        return numRowsDeleted;
    }

}
