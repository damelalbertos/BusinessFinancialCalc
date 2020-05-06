import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {



    @Test
    public void getIdTest(){
//
//        CentralBusiness business = new CentralBusiness("BusinessName");
//
//        Employee employee1 = new Employee("1000", 11.25, 36);
//        Employee employee2 = new Employee("1001", 13.25, 50);
//        Employee employee3 = new Employee("1002", 10, 40);
//
//
//
//        business.addAccount("1000", employee1);
//        business.addAccount("1001", employee2);
//        business.addAccount("1002", employee3);
//
//
//        assertEquals("1000", business.getEmployee("1000").getId());
//        assertEquals("1001", business.getEmployee("1001").getId());
//        assertEquals("1002",business.getEmployee("1002").getId());
//
//
//


    }


    @Test
    public void getHoursWorked(){
        CentralBusiness business = new CentralBusiness("BusinessName");
        Employee employee1 = new Employee();
        employee1.setId("1000");
        employee1.setWage(11.25);
        employee1.setHoursWorked(36);
        Employee employee2 = new Employee();
        employee2.setId("1001");
        employee2.setWage(13.25);
        employee2.setHoursWorked(50);
        Employee employee3 = new Employee();
        employee3.setId("1002");
        employee3.setWage(10);
        employee3.setHoursWorked(40);
        Employee employee4 = new Employee();
        employee4.setId("1003");
        employee4.setWage(20);
        employee4.setHoursWorked(40);


        business.addAccount("1000", employee1);
        business.addAccount("1001", employee2);
        business.addAccount("1002", employee3);



        assertEquals(36, business.getEmployee("1000").getHoursWorked());
        assertEquals(50, business.getEmployee("1001").getHoursWorked());
        assertEquals(40, business.getEmployee("1002").getHoursWorked());

        assertThrows(IllegalArgumentException.class, () -> business.getEmployee("12342").getHoursWorked());
        assertThrows(IllegalArgumentException.class, () -> business.getEmployee("0").getHoursWorked());
    }







}
