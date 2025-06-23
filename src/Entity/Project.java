package Entity;

import java.util.ArrayList;
import java.util.List;

/*
    Project Class will store ProjectName , Manager whom the project is assigned
    and list of employees who will work on this project.
 */
public class Project {
    private String projectName;
    private Manager manager;
    private List<Employee> assignedEmployees;

    public Project(String projectName , Manager manager){
        this.projectName = projectName;
        this.manager = manager;
        this.assignedEmployees = new ArrayList<>();
    }

    public boolean assignEmployees(Employee e){
        if(!(manager.getTeamMembers().contains(e))){
            System.out.println("Employee is not under this manager");
            return false;
        }
        assignedEmployees.add(e);
        return true;
    }
    public String getProjectName() {return  projectName;}
    public Manager getManager() {return  manager;}
    public List<Employee> getAssignedEmployees(){return  assignedEmployees;}

    @Override
    public String toString(){
        return "Project name: " + projectName + " , Manager: " + manager.getName();
    }
}
