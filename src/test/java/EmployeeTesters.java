import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTesters {



    @Test
    public void getInfoTest(){





    }


    @Test
    public void getHoursWorked(){
        BusinessTracker business = new BusinessTracker("BusinessName");
        Employees employee1 = new Employees("1000", 11.25, 36);
        Employees employee2 = new Employees("1001", 13.25, 50);
        Employees employee3 = new Employees("1002", 10, 40);


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
