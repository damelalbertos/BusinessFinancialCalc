import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {



    @Test
    public void getInfoTest(){





    }


    @Test
    public void getHoursWorked(){
        CentralBusiness business = new CentralBusiness("BusinessName");
        Employee employee1 = new Employee("1000", 11.25, 36);
        Employee employee2 = new Employee("1001", 13.25, 50);
        Employee employee3 = new Employee("1002", 10, 40);
        Employee employee4 = new Employee("1002", 20, 40);


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
