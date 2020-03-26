import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTesters {


    @Test
    public void  calcPayTest(){
        Employees employee1 = new Employees("1000", 11.25, 36);
        Employees employee2 = new Employees("1001", 13.25, 50);
        Employees employee3 = new Employees("1002", 10, 40);

        assertEquals(405, employee1.calcPay("1000"));
        assertEquals(738.7, employee2.calcPay("1001"));
        assertEquals(400, employee3.calcPay("1002"));



    }

    @Test
    public void calcOvertimePayTest(){


    }


    @Test
    public void getInfoTest(){





    }


}
