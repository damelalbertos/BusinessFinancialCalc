public class Employee {

    private String id;
    private double wage;
    private double hoursWorked;

    //private CentralBusiness business;

    public Employee(){
        this.id = "";
        this.wage = 0.0;
        this.hoursWorked = 0.0;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public void setHoursWorked(double hoursWorked) {
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
