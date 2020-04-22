import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Customer {


    private String fName;
    private String lName;
    private String customerID;;

    public Customer(String fName, String lName, String customerID) {
        this.fName = fName;
        this.lName = lName;
        this.customerID = customerID;
    }

    public String getfName() {
        return fName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getlName() {
        return lName;
    }
}

