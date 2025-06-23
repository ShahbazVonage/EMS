package Service;

import Entity.Employee;
import Entity.Manager;
import Entity.Person;
import Entity.Project;
import Exceptions.DuplicateEmployeeException;
import Exceptions.InvalidAgeException;
import Exceptions.InvalidSalaryException;

import java.util.*;
import java.util.stream.Collectors;

/*
    It is a service layer where all the implementation of the logic take place
    like -> addEmployee , addManager etc.
 */
public class EmsService {
    Scanner sc = new Scanner(System.in);
    List<Employee> employeeList = new ArrayList<>();
    List<Manager> managerList = new ArrayList<>();
    List<Project> projectList = new ArrayList<>();

    /*
      Employee is added here, user will enter the information like name , age etc.
      and the employee with the information will be created.
   */
    public void addEmployee(){
        System.out.println("Employee ID: ");
        int id = sc.nextInt(); sc.nextLine();

        try {
            checkEmployeeByIdExist(id);

            System.out.println("Name: ");
            String name = sc.nextLine();
            System.out.println("Age: ");
            int age = sc.nextInt();
            checkAge(age);
            System.out.println("Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();
            checkSalary(salary);
            System.out.println("Department: ");
            String dept = sc.nextLine();

            employeeList.add(new Employee(id, name, age, salary, dept));
            System.out.println("Employee added Successfully.");
        }catch (DuplicateEmployeeException | InvalidSalaryException | InvalidAgeException e){
            System.out.println("Error: " + e.getMessage());
        }


    }
    /*
   Manager is added here, user will enter the information like name , age etc.
   and the manager with the information will be created
    */
    public void addManager(){
        System.out.println("Manager ID: ");
        int id = sc.nextInt(); sc.nextLine();
        try {
            checkManagerByIdExist(id);

            System.out.println("Name: ");
            String name = sc.nextLine();
            System.out.println("Age: ");
            int age = sc.nextInt();
            checkAge(age);
            System.out.println("Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();
            checkSalary(salary);
            System.out.println("Department: ");
            String dept = sc.nextLine();

            managerList.add(new Manager(id, name, age, salary, dept));
            System.out.println("Manager added Successfully");
        } catch (DuplicateEmployeeException | InvalidAgeException | InvalidSalaryException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    /*
        We can assign managers to employees
     */
    public void assignEmployeeToManager(){
        displayManagers();
        System.out.println("Manager ID: ");
        int managerId = sc.nextInt(); sc.nextLine();
        displayEmployees();
        System.out.println("Employee ID: ");
        int empId = sc.nextInt();sc.nextLine();

        Optional<Manager> manager = findManagerById(managerId);
        Optional<Employee> employee = findEmployeeById(empId);

        if(manager.isPresent() && employee.isPresent()){
            try{
                manager.get().addTeamMember(employee.get());
                System.out.println("Employee assigned to Manager");
            }catch (DuplicateEmployeeException e){
                System.out.println("Error: "  + e.getMessage());
            }
        }else {
            System.out.println("Either Employee or Manager not found.");
        }
    }
    /*
      Project creation and the project is assigned to a Manager.
   */
    public void createProject(){
        System.out.println("Project Name: ");
        String name = sc.nextLine();
        displayManagers();
        System.out.println("Manager Id: ");
        int manId = sc.nextInt(); sc.nextLine();

        Optional<Manager> manager = findManagerById(manId);
        if(manager.isPresent()){
            projectList.add(new Project(name , manager.get()));
            System.out.println("Project Created and assigned to manager");
        }else {
            System.out.println("Manager not found.");
        }
    }
    /*
       Project assign to an employee.
       You have a list of projects from which you can choose the project and
       after project selection you choose an employee whom to assign this project.
    */
    public void assignEmployeeToProject(){
        System.out.println("Project Name: ");
        String name = sc.nextLine();
        System.out.println("Employee ID: ");
        int empId = sc.nextInt(); sc.nextLine();

        Optional<Project> project = findProjectByName(name);
        Optional<Employee> employee = findEmployeeById(empId);

        if(project.isPresent() && employee.isPresent()){
            boolean assigned = project.get().assignEmployees(employee.get());
            if(assigned) System.out.println("Project is assigned to Employee");
            else System.out.println("Project assignment is failed");
        }else {
            System.out.println("Project or Employee not found");
        }
    }
    /*
       This method will you give option to choose manager and after that
       it will provide you the employees under that manager.
     */
    public void showManagerTeam(){
        displayManagers();
        System.out.println("Manager Id: ");
        int manId = sc.nextInt(); sc.nextLine();

        Optional<Manager> manager = findManagerById(manId);

        if(manager.isPresent()){
            List<Employee> employees = manager.get().getTeamMembers();
            if (employees.isEmpty()) System.out.println("No Employees assigned.");
            else{
                employees.forEach(System.out::println);
            }
        }else{
            System.out.println("Manager not found");
        }
    }
    /*
       This method provide the highest salary, first you have to enter
       a specific amount from that you want to get list of employees
    */
    public void filterHighSalaryEmployees(){
        System.out.println("Enter Maximum amount form which you want higher salary employees");
        double threshold = sc.nextDouble(); sc.nextLine();

        employeeList.
                stream().
                filter(e -> e.getSalary() > threshold).
                forEach(System.out::println);
    }
    /*
      This method sort employees based on their name or age
      User can choose whichever sorting method he/she needs.
   */
    public void sortEmployees(){
        System.out.println("Sort employees by name or age");
        String sortBy = sc.nextLine();

        List<Employee> sorted;

        if(sortBy.equalsIgnoreCase("name")){
            sorted = employeeList.
                    stream().
                    sorted(Comparator.comparing(Person::getName)).toList();
        }else if (sortBy.equalsIgnoreCase("age")){
            sorted = employeeList.stream()
                    .sorted(Comparator.comparing(Person::getAge)).toList();
        }else{
            System.out.println("Invalid sort option");
            return;
        }
        sorted.forEach(System.out::println);
    }
    /*
        This method group employees on the basis of their department.
     */
    public void groupEmployeesByDepartment(){
        Map<String , List<Employee>> grouped = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        grouped.forEach((dept , list) -> {
            System.out.println("Department: " + dept);
            list.forEach(System.out::println);
        });
    }

    public void managerEmployeeHierarchy(){
        if(managerList.isEmpty()){
            System.out.println("No managers are available.");
            return;
        }
        for(Manager manager : managerList){
            System.out.println("Manager: " + manager.getName() + " (ID: " + manager.getEmployeeId()+")");
            System.out.println(" ↓");

            List<Employee> team = manager.getTeamMembers();
            if(team.isEmpty()) System.out.println("  → No employees assigned.");
            else{
                for(Employee emp : team){
                    System.out.println("  → Employee: " + emp.getName() + " (ID: " + emp.getEmployeeId() + ")");
                }
            }
            System.out.println();
        }
    }
    /*
    THis method groups department and give average salary of the respective department
     */
    public void averageSalaryByDepartment(){
        if(employeeList.isEmpty()){
            System.out.println("No employees available");
            return;
        }
        System.out.println("=== Average Salary by Department ===");

        Map<String , Double> avgSalaryByDepart = employeeList.stream().
                collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        avgSalaryByDepart.forEach((dept , avgSalary) ->{
            System.out.printf("Department: %-15s | Average Salary: %.2f%n", dept, avgSalary);
        });
    }


    // Finding Manager by manager Id
    public Optional<Manager> findManagerById(int id){
        return managerList.
                stream().
                filter(m -> m.getEmployeeId() == id).findFirst();
    }
    // Finding employee by employee Id
    public Optional<Employee> findEmployeeById(int id){
        return employeeList.stream()
                .filter(e -> e.getEmployeeId() == id).findFirst();
    }
    // Finding Project by project name
    public Optional<Project> findProjectByName(String name){
        return projectList.stream()
                .filter(p -> p.getProjectName().equals(name)).findFirst();
    }
    // Checking employee already exist by that employee Id or not
    public void checkEmployeeByIdExist(int id) throws DuplicateEmployeeException {
        if(findEmployeeById(id).isPresent()){
            throw new DuplicateEmployeeException("This Id already Exist: " + id);
        }
    }
    // Checking manager already exist by that manager Id or not
    public void checkManagerByIdExist(int id) throws DuplicateEmployeeException {
        if(findManagerById(id).isPresent()){
            throw new DuplicateEmployeeException("This Id already Exist: " + id);
        }
    }
    //Check for age , user input negative it will throw exception
    public void checkAge(int age) throws InvalidAgeException {
        if(age < 0){
            throw new InvalidAgeException("Age is not valid: " + age);
        }
    }
    //Check for salary , user input negative it will throw exception
    public void checkSalary(double salary) throws InvalidSalaryException {
        if(salary < 0){
            throw new InvalidSalaryException("Salary is not valid: " + salary);
        }
    }
    public void displayManagers(){
        System.out.println("All managers");
        managerList.forEach(System.out::println);
    }
    public void displayEmployees(){
        System.out.println("All employees");
        employeeList.forEach(System.out::println);
    }
}
