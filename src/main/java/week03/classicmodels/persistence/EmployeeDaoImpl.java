package week03.classicmodels.persistence;


import week03.classicmodels.business.Employee;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/classicmodels";
        String username = "root";
        String password = "";
        try{
            Class.forName(driver);

            try(Connection conn = DriverManager.getConnection(url, username, password)){
                try(PreparedStatement ps = conn.prepareStatement("SELECT * from Employees")){
                    try(ResultSet rs = ps.executeQuery()){
                        while(rs.next()){
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
                    }catch(SQLException e){
                        System.out.println(LocalDateTime.now() + ": An SQLException  occurred while running the query" +
                                " or processing the result.");
                        System.out.println("Error: " + e.getMessage());
                        e.printStackTrace();
                    }
                }catch(SQLException e){
                    System.out.println(LocalDateTime.now() + ": An SQLException  occurred while preparing the SQL " +
                            "statement.");
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }catch(SQLException e){
                System.out.println(LocalDateTime.now() + ": An SQLException  occurred while trying to connect to the " + url +
                        "database.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }catch(ClassNotFoundException e){
            System.out.println(LocalDateTime.now() + ": A ClassNotFoundException occurred while trying to load the MySQL driver.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return employees;
    }

    public Employee getById(int id){
        Employee emp = null;

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/classicmodels";
        String username = "root";
        String password = "";
        try{
            Class.forName(driver);

            try(Connection conn = DriverManager.getConnection(url, username, password)){
                try(PreparedStatement ps =
                            conn.prepareStatement("SELECT * from Employees where employeeNumber = ?")){
                    ps.setInt(1, id);
                    try(ResultSet rs = ps.executeQuery()){
                        if(rs.next()){
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
                    }catch(SQLException e){
                        System.out.println(LocalDateTime.now() + ": An SQLException  occurred while running the query" +
                                " or processing the result.");
                        System.out.println("Error: " + e.getMessage());
                        e.printStackTrace();
                    }
                }catch(SQLException e){
                    System.out.println(LocalDateTime.now() + ": An SQLException  occurred while preparing the SQL " +
                            "statement.");
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }catch(SQLException e){
                System.out.println(LocalDateTime.now() + ": An SQLException  occurred while trying to connect to the " + url +
                        "database.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }catch(ClassNotFoundException e){
            System.out.println(LocalDateTime.now() + ": A ClassNotFoundException occurred while trying to load the MySQL driver.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return emp;
    }

    public static void main(String[] args) {
        EmployeeDao empDao = new EmployeeDaoImpl();
        System.out.println(empDao.getAllEmployees());
        System.out.println("---------------------------------------");
        System.out.println("No such employee: " + empDao.getById(1));
        System.out.println("Employee exist: " + empDao.getById(1625));
    }

}
