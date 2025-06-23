package Entity;

/*
    Employee Class is inheriting Person so we have get empId, name, age and
    employee have their department and salary
 */
public class Employee extends Person {
    private double salary;
    private String department;
    private int employeeId;


    public Employee(int employeeId, String name, int age, double salary , String department) {
        super(name, age);
        this.department = department;
        this.salary = salary;
        this.employeeId = employeeId;
    }
    public double getSalary() {return salary;}
    public String getDepartment() {return department;}
    public int getEmployeeId() { return employeeId; }

    @Override
    public String toString(){
        return "Employee ID: "+ employeeId +","+ super.toString()+ " " +"Salary: "+ salary + " " +"department: " +department + " ";
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Employee)) return false;
        Employee e = (Employee) obj;
        return this.employeeId == e.employeeId;
    }
}
