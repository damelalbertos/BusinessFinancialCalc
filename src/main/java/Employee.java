public class Employee {

    private String id;
    private double wage;
    private double hoursWorked;

    private CentralBusiness business;


    public Employee(CentralBusiness business){
        this.business = business;
    }



    public Employee(String id, double wage, double hoursWorked){
        this.id = id;
        this.wage = wage;
        this.hoursWorked = hoursWorked;
    }


    public double getWage() throws IllegalArgumentException{
        return wage;
    }




    /**
     *
     *
     */
    public double getHoursWorked() throws IllegalArgumentException{



        return hoursWorked;


    }






    /**
     *
     *
     * @return
     */
    public String getId() throws IllegalArgumentException{




        return id;
    }




}
