package week02.classicmodels.applications;

import week02.classicmodels.business.Employee;
import week02.classicmodels.persistence.EmployeeDao;
import week02.classicmodels.persistence.EmployeeDaoImpl;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SelectAllEmployees {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        List<Employee> employees = employeeDao.getAllEmployees();

        System.out.println(employees);
    }
}
