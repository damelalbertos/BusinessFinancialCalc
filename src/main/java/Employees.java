import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Employees  {

    private String id;
    private double wage;
    private double hoursWorked;

    private Map<String, Employees> employeesMap = new HashMap<>();




    public Employees(String id, double wage, double hoursWorked){
        this.id = id;
        this.wage = wage;
        this.hoursWorked = hoursWorked;

    }



    /**
     * Description: Calculates pay for employee based on their Id
     *
     * @param
     */
    public double calcPay(String id) throws IllegalArgumentException{
        double hours = employeesMap.get(id).hoursWorked;
        double wage = employeesMap.get(id).wage;
        double pay = 0;

        if(!employeesMap.containsKey(id)){
            throw new IllegalArgumentException("Employee with id" + id + "doesn't exists");
        }
        if(hours <= 40){
            pay = wage*hours;
        }

        else {
            pay = wage*40 + calcOvertimePay(hours-40);



        }

//        DecimalFormat newFormat = new DecimalFormat("#. ##");
//
//
//        balance = Double.valueOf(newFormat.format(balance));
//
//        toAccount.balance = Double.valueOf(newFormat.format(toAccount.balance));
//

        return pay;



    }

    /**
     *
     *
     */
    public double getHoursWorked() throws IllegalArgumentException{

        if(!employeesMap.containsKey(id)){
            throw new IllegalArgumentException("Employee with id" + id + "doesn't exists");
        }
        return employeesMap.get(id).hoursWorked;


    }


    public void removeEmployee(String id, Employees employee){
        employeesMap.remove(id, employee);
    }

    /**
     *
     *
     */
    public double calcOvertimePay(double hours){
        hours = employeesMap.get(id).hoursWorked-40;
        double wage = employeesMap.get(id).wage * 1.5;

        double overtimePay = wage*hours;

        return overtimePay;



    }


    /**
     *
     *
     * @return
     */
    public String getInfo() throws IllegalArgumentException{

        if(!employeesMap.containsKey(id)){
            throw new IllegalArgumentException("Employee with id" + id + "doesn't exists");
        }


        return "doSomething";
    }

}
