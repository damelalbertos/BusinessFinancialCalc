import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void incrementCustomerIDTest() {
        //test that orderID set to 1 if no numeric string value
        Order order = new Order("1", "", 1.00);
        order.incrementCustomerID();
        assertEquals("0", order.getCustomerID());

        //Check that gets incremented by 1 if between 0-999
        Order order1 = new Order("1", "200", 1.00);
        order1.incrementCustomerID();
        assertEquals("201", order1.getCustomerID());

        //Check that gets reset to 0 if goes over 999
        Order order2 = new Order("1", "999", 1.00);
        order2.incrementCustomerID();
        assertEquals("0", order2.getCustomerID());

    }

    @Test
    public void incrementOrderIDTest() {
        //test that orderID set to 1 if no numeric string value
        Order order = new Order("", "100", 1.00);
        order.incrementOrderID();
        assertEquals("0", order.getOrderID());

        //Check that gets incremented by 1 if between 0-999
        Order order1 = new Order("571", "100", 1.00);
        order1.incrementOrderID();
        assertEquals("572", order1.getOrderID());

        Order order2 = new Order("999", "100", 1.00);
        order2.incrementOrderID();
        assertEquals("0", order2.getOrderID());


    }

}
