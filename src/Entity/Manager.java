package Entity;

import Exceptions.DuplicateEmployeeException;

import java.util.ArrayList;
import java.util.List;

/*
    Manager class is inheriting employee class for getting salary and department
    it has a list of employees who are under the manager
 */
public class Manager extends Employee{
    private List<Employee> teamMembers ;
    public Manager(int employeeId, String name, int age, double salary, String department) {
        super(employeeId , name, age, salary, department);
        this.teamMembers = new ArrayList<>();
    }

    public void addTeamMember(Employee e) throws DuplicateEmployeeException {
        if(teamMembers.contains(e)){
            throw new DuplicateEmployeeException("Employee with ID " + e.getEmployeeId() +" Already exist");
        }
        teamMembers.add(e);
    }
    public List<Employee> getTeamMembers() {return teamMembers;}

    @Override
    public String toString(){
        return " Manager -> " + super.toString();
    }
}
