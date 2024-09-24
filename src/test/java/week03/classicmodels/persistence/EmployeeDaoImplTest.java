package week03.classicmodels.persistence;

import week03.classicmodels.business.Employee;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoImplTest {
    @org.junit.jupiter.api.Test
    void getAllEmployees_AllFound() {
        EmployeeDao empDao = new EmployeeDaoImpl("test_classicmodels");

        List<Employee> expected = generateAllResults();
        List<Employee> results = empDao.getAllEmployees();
        assertEquals(23, results.size());

        // THIS IS A HACK - I DON'T WANT TO CREATE 23 EMPLOYEES BY HAND!!
        // Normally we would do the full length of the list
        for (int i = 0; i < 6; i++) {
            assertEmployeeEquals(expected.get(i),results.get(i));
        }
    }

    @org.junit.jupiter.api.Test
    void getById_NoMatchFound() {
        EmployeeDao empDao = new EmployeeDaoImpl("test_classicmodels");
        Employee result = empDao.getById(1);
        assertNull(result);
    }

    @org.junit.jupiter.api.Test
    void getById_MatchFound() {
        EmployeeDao empDao = new EmployeeDaoImpl("test_classicmodels");
        Employee expected = new Employee(1625, "Kato", "Yoshimi", "x102", "ykato@classicmodelcars.com", "5", 1621,
                "Sales Rep");
        Employee result = empDao.getById(1625);
        assertEmployeeEquals(expected, result);
    }

    void assertEmployeeEquals(Employee expected, Employee result){
        assertEquals(expected.getEmployeeNumber(), result.getEmployeeNumber());
        assertEquals(expected.getLastName(), result.getLastName());
        assertEquals(expected.getFirstName(), result.getFirstName());
        assertEquals(expected.getEmail(), result.getEmail());
        assertEquals(expected.getExtension(), result.getExtension());
        assertEquals(expected.getJobTitle(), result.getJobTitle());
        assertEquals(expected.getReportsTo(), result.getReportsTo());
        assertEquals(expected.getOfficeCode(), result.getOfficeCode());
    }

    List<Employee> generateAllResults(){
        List<Employee> results = new ArrayList();

        Employee e1 = new Employee(1002, "Murphy",  "Diane", "x5800", "dmurphy@classicmodelcars.com", "1", 0,
                "President");
        Employee e2 = new Employee(1056, "Patterson",  "Mary", "x4611", "mpatterso@classicmodelcars.com", "1", 1002,
                "VP Sales");
        Employee e3 = new Employee(1076, "Firrelli", "Jeff", "x9273", "jfirrelli@classicmodelcars.com", "1", 1002,
                "VP Marketing");
        Employee e4 = new Employee(1088, "Patterson", "William", "x4871", "wpatterson@classicmodelcars.com", "6",
                1056, "Sales Manager (APAC)");
        Employee e5 = new Employee(1102, "Bondur", "Gerard", "x5408", "gbondur@classicmodelcars.com", "4", 1056,
                "Sale Manager (EMEA)");
        Employee e6 = new Employee(1143, "Bow", "Anthony", "x5428", "abow@classicmodelcars.com", "1", 1056, "Sales " +
                "Manager (NA)");

        results.add(e1);
        results.add(e2);
        results.add(e3);
        results.add(e4);
        results.add(e5);
        results.add(e6);
        return results;
    }
}