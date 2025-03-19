/*Question 1:- Write a Java program to calculate the sum of a list of integers using autoboxing and unboxing. Include methods to parse strings into their respective wrapper classes (e.g., Integer.parseInt()).
Code:-*/
import java.util.List;
import java.util.ArrayList;

public class AutoboxingUnboxingSum {
    public static Integer parseInteger(String str) {
        try {
            return Integer.parseInt(str); 
        } catch (NumberFormatException e) {
            System.out.println("Invalid number: " + str);
            return null;
        }
    }
    public static int calculateSum(List<Integer> numbers) {
        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        String[] stringNumbers = {"10", "20", "30", "40", "50"};
        List<Integer> numberList = new ArrayList<>();
        for (String str : stringNumbers) {
            Integer number = parseInteger(str);
            if (number != null) {
                numberList.add(number); 
            }}
        int sum = calculateSum(numberList);
        System.out.println("The sum of the list is: " + sum);
    }
}

/*Output:- The sum of the list is: 150*/

/*Question 2:-Create a Java program to serialize and deserialize a Student object. The program should Serialize a Student object (containing id, name, and GPA) and save it to a file.Deserialize the object from the file and display the student details. Handle FileNotFoundException, IOException, and ClassNotFoundException using exception handling.
Code:-*/
import java.io.*;
class Student implements Serializable {
    private int id;
    private String name;
    private double GPA;
    public Student(int id, String name, double GPA) {
        this.id = id;
        this.name = name;
        this.GPA = GPA;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getGPA() {
        return GPA;
    }
    public void displayDetails() {
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Student GPA: " + GPA);
    }}
public class SerializeDeserializeStudent {
    public static void main(String[] args) {
        Student student = new Student(1, "John Doe", 3.85);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            out.writeObject(student);  // Serialize the object
            System.out.println("Student object serialized and saved to file.");
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found!");
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student deserializedStudent = (Student) in.readObject();  // Deserialize the object
            System.out.println("\nDeserialized Student Details:");
            deserializedStudent.displayDetails();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found for deserialization!");
        } catch (IOException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Class not found during deserialization!");
        }
    }
}

/*Output:-
  Student object serialized and saved to file.

Deserialized Student Details:
Student ID: 1
Student Name: John Doe
Student GPA: 3.85
*/

/*Question 3:- Create a menu-based Java application with the following options. 1.Add an Employee 2. Display All 3. Exit If option 1 is selected, the application should gather details of the employee like employee name, employee id, designation and salary and store it in a file. If option 2 is selected, the application should display all the employee details. If option 3 is selected the application should exit.
  Code:-*/
import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private String name;
    private int id;
    private String designation;
    private double salary;
    public Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }

    public void displayDetails() {
        System.out.println("Employee ID: " + id);
        System.out.println("Employee Name: " + name);
        System.out.println("Employee Designation: " + designation);
        System.out.println("Employee Salary: " + salary);
        System.out.println("------------");
    }
}

public class EmployeeManagementApp {
    public static void addEmployee(List<Employee> employees) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  
        System.out.println("Enter Employee Designation: ");
        String designation = scanner.nextLine();
        System.out.println("Enter Employee Salary: ");
        double salary = scanner.nextDouble();
        Employee employee = new Employee(name, id, designation, salary);
        employees.add(employee);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employees.ser"))) {
            out.writeObject(employees);
            System.out.println("Employee added successfully.");
        } catch (IOException e) {
            System.err.println("Error saving employee details to file.");
        }
    }
    public static void displayAllEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employees) {
                employee.displayDetails();
            }
        }
    }
    public static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("employees.ser"))) {
            employees = (List<Employee>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous records found.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading employee data.");
        }

        return employees;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = loadEmployees();  

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addEmployee(employees);
                    break;

                case 2:
                    displayAllEmployees(employees);
                    break;

                case 3:
                    System.out.println("Exiting application.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }}}}
/*Output:-
No previous records found.
Menu:
1. Add an Employee
2. Display All Employees
3. Exit
Enter your choice: 2
No employees found.
Menu:
1. Add an Employee
2. Display All Employees
3. Exit
Enter your choice: 3
Exiting application.*/
