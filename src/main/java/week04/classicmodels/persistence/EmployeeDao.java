package week04.classicmodels.persistence;

import week04.classicmodels.business.Employee;

import java.util.List;

public interface EmployeeDao {
    public List<Employee> getAllEmployees();
    public Employee getById(int id);
    public boolean updateEmployeeEmail(int id, String email);
}
