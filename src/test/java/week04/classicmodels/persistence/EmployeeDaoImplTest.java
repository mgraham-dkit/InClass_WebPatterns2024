package week04.classicmodels.persistence;

import org.junit.jupiter.api.Test;
import week04.classicmodels.business.Employee;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoImplTest {

    @Test
    void updateEmployeeEmail_ValidID() {
        EmployeeDao empDao = new EmployeeDaoImpl("database_test.properties");
        int empNum = 1002;
        String newEmail = "hello@gmail.com";
        boolean result = empDao.updateEmployeeEmail(empNum, newEmail);
        
        assertTrue(result);

        // PHASE TWO: Ripple effect check
        
        Employee edited = empDao.getById(empNum);
        assertEquals(empNum, edited.getEmployeeNumber());
        assertEquals(newEmail, edited.getEmail());
    }
}