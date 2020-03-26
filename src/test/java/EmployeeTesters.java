import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTesters {


    @Test
    public void  calcPayTest(){

        BusinessTracker business = new BusinessTracker();

        Employees employee1 = new Employees("1000", 11.25, 36);
        Employees employee2 = new Employees("1001", 13.25, 50);
        Employees employee3 = new Employees("1002", 10, 40);
        Employees employee4 = new Employees("1003", 15.37, 27);
        Employees employee5 = new Employees("1004", 14.26, 42);
        business.addAccount("1000", employee1);
        business.addAccount("1001", employee2);
        business.addAccount("1002", employee3);
        business.addAccount("1003", employee4);
        business.addAccount("1004", employee5);


        assertEquals(405, business.calcPay("1000"));
        assertEquals(728.75, business.calcPay("1001"));
        assertEquals(400, business.calcPay("1002"));
        assertEquals(414.99, business.calcPay("1003"));
        assertEquals(613.18, business.calcPay("1004"));
        assertThrows(IllegalArgumentException.class, () -> business.calcPay("134324"));
        assertThrows(IllegalArgumentException.class, () -> business.calcPay("0"));






    }

    @Test
    public void calcOvertimePayTest(){

        BusinessTracker business = new BusinessTracker();

        Employees employee = new Employees("100", 14.26, 42);
        Employees employee2 = new Employees("1001", 13.25, 50);

        business.addAccount("100", employee);
        business.addAccount("1001", employee2);

        assertEquals(42.78, business.calcOvertimePay("100", 2));
        assertEquals(198.75, business.calcOvertimePay("1001", 10));

    }


    @Test
    public void getInfoTest(){





    }


    @Test
    public void getHoursWorked(){
        BusinessTracker business = new BusinessTracker();
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
