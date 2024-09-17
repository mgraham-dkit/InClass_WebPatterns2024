package week02.classicmodels.persistence;

import week02.classicmodels.business.Employee;

import java.util.List;

public interface EmployeeDao {
    public List<Employee> getAllEmployees();
}
