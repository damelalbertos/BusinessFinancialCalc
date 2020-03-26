public class Employees {

    private String id;
    private double wage;
    private double hoursWorked;

    protected BusinessTracker business;


    public Employees(BusinessTracker business){
        this.business = business;
    }


    public Employees(String id, double wage, double hoursWorked){
        this.id = id;
        this.wage = wage;
        this.hoursWorked = hoursWorked;
    }




//
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
    public String getInfo() throws IllegalArgumentException{

        if(!business.exists(id)){
            throw new IllegalArgumentException("Employee with id" + id + "doesn't exists");
        }


        return "doSomething";
    }

}
