import Service.EmsService;

import java.util.Scanner;

/*
    Employee Management System -> where a manager , employee and project created and
    the project first assigned to manager and after that project can be assigned to employee
    then that employee will come under that manager.
    Features :->
        1. Find a list of employees under a manager
        2. We can Filter high salary by entering from which amount you need get the highest salary list
        3. We can sort employees using age or name
        4. We can Group employees by department etc.
 */

public class EMS {
    private  static  final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        EmsService service = new EmsService();

        while (true){
            System.out.println("\n=== EMS MENU ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Add Manager");
            System.out.println("3. Assign Employee to Manager");
            System.out.println("4. Create Project and Assign to Manager");
            System.out.println("5. Assign Employee to Project");
            System.out.println("6. Show Employees under a Manager");
            System.out.println("7. Filter High Salary Employees");
            System.out.println("8. Sort Employees (by name/age)");
            System.out.println("9. Group Employees by Department");
            System.out.println("10. Show Manager-Employee Hierarchy");
            System.out.println("11. Calculate average salary of departments");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");

            switch (sc.nextInt()) {
                case 1 -> service.addEmployee();
                case 2 -> service.addManager();
                case 3 -> service.assignEmployeeToManager();
                case 4 -> service.createProject();
                case 5 -> service.assignEmployeeToProject();
                case 6 -> service.showManagerTeam();
                case 7 -> service.filterHighSalaryEmployees();
                case 8 -> service.sortEmployees();
                case 9 -> service.groupEmployeesByDepartment();
                case 10 -> service.managerEmployeeHierarchy();
                case 11 -> service.averageSalaryByDepartment();
                case 0 -> {
                    System.out.println("EMS exit. Bye");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
