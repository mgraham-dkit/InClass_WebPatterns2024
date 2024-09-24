package week03.classicmodels.persistence;


import week03.classicmodels.business.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl extends MySQLDao implements EmployeeDao {
    public EmployeeDaoImpl(String databaseName){
        super(databaseName);
    }
    
    public EmployeeDaoImpl(){
        super();
    }

    public static void main(String[] args) {
        EmployeeDao empDao = new EmployeeDaoImpl();
        System.out.println(empDao.getAllEmployees());
        System.out.println("---------------------------------------");
        System.out.println("No such employee: " + empDao.getById(1));
        System.out.println("Employee exist: " + empDao.getById(1002));
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        Connection conn = super.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * from Employees")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee e = new Employee(
                            rs.getInt("employeeNumber"),
                            rs.getString("lastName"),
                            rs.getString("firstName"),
                            rs.getString("extension"),
                            rs.getString("email"),
                            rs.getString("officeCode"),
                            rs.getInt("reportsTo"),
                            rs.getString("jobTitle")
                    );
                    employees.add(e);
                }
            } catch (SQLException e) {
                System.out.println(LocalDateTime.now() + ": An SQLException  occurred while running the query" +
                        " or processing the result.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": An SQLException  occurred while preparing the SQL " +
                    "statement.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return employees;
    }

    public Employee getById(int id) {
        Employee emp = null;

        Connection conn = super.getConnection();
        try (PreparedStatement ps =
                     conn.prepareStatement("SELECT * from Employees where employeeNumber = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    emp = new Employee(
                            rs.getInt("employeeNumber"),
                            rs.getString("lastName"),
                            rs.getString("firstName"),
                            rs.getString("extension"),
                            rs.getString("email"),
                            rs.getString("officeCode"),
                            rs.getInt("reportsTo"),
                            rs.getString("jobTitle")
                    );
                }
            } catch (SQLException e) {
                System.out.println(LocalDateTime.now() + ": An SQLException  occurred while running the query" +
                        " or processing the result.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": An SQLException  occurred while preparing the SQL " +
                    "statement.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        return emp;
    }
}
