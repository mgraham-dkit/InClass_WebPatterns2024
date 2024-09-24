package week03.classicmodels.persistence;

import week03.classicmodels.business.Employee;

import java.util.List;

public interface EmployeeDao {
    public List<Employee> getAllEmployees();
    public Employee getById(int id);
}
