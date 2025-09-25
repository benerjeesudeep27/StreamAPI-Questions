import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.*;
import java.util.*;

public class Main_2 {
    public static void main(String[] args) {
        List<Employee> empList = Arrays.asList(
                new Employee(101, "Amit Sharma", "Male", 28, "IT", 65000, 2015),
                new Employee(102, "Priya Verma", "Female", 32, "HR", 58000, 2010),
                new Employee(103, "Ravi Kumar", "Male", 45, "Finance", 72000, 2020),
                new Employee(104, "Sneha Joshi", "Female", 29, "Marketing", 60000, 2016),
                new Employee(105, "Arjun Mehta", "Male", 35, "Sales", 55000, 2012),
                new Employee(106, "Neha Singh", "Female", 26, "IT", 67000, 2013),
                new Employee(107, "Karan Patel", "Male", 40, "Operations", 70000, 2017),
                new Employee(108, "Divya Nair", "Female", 31, "HR", 59000, 2023),
                new Employee(109, "Manish Gupta", "Male", 38, "Finance", 75000, 2022),
                new Employee(110, "Ritika Das", "Female", 27, "Marketing", 61000, 2011),
                new Employee(111, "Siddharth Roy", "Male", 30, "Sales", 56000, 2014),
                new Employee(112, "Anjali Jain", "Female", 33, "IT", 68000, 2016),
                new Employee(113, "Vikram Chauhan", "Male", 42, "Operations", 71000, 2021),
                new Employee(114, "Meera Iyer", "Female", 25, "HR", 57000, 2023),
                new Employee(115, "Rajesh Thakur", "Male", 37, "Finance", 74000, 2009),
                new Employee(113, "Purbasa Banerjee", "Female", 32, "Operations", 51000, 2020)
        );
        //1. Count male and female employees in the organization
        long empCount = empList.stream().map(x -> x.getGender()).count();
        System.out.println(empCount);
        //Male & Female Separate :-
        long maleCount = empList.stream().filter(x -> x.getGender().equalsIgnoreCase("male")).count();
        long femaleCount = empList.stream().filter(x -> x.getGender().equalsIgnoreCase("female")).count();
        System.out.println("Male : " + maleCount + ", Female : " + femaleCount);
        System.out.println("===========================================");

        //2. Get the average age of male and female employees
        double aveAge = empList.stream().mapToInt(x -> x.getAge()).average().orElse(0.0);
        System.out.println("Average age : " + aveAge);
        //Male & Female Separate:-
        Map<String, Double> genderAvrAge = empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        //Map<String, Double> genderAvrAge_2 = empList.stream().collect(Collectors.groupingBy(Employee :: getGender, Collectors.averagingInt(Employee :: getAge)));
        for (Map.Entry<String, Double> entry : genderAvrAge.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("=====================================");

        //Important:-
        //3. Find the department with the highest number of employees
        String deptName = empList.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry :: getKey).orElse(null);
        System.out.println("Highest No. of Emp :-"+deptName);
        System.out.println("=====================================");
        List<String> departmentsWithMaxEmployees = empList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
                .entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue)) // group by employee count
                .entrySet().stream()
                .max(Map.Entry.comparingByKey()) // pick the max count
                .map(e -> e.getValue().stream()
                        .map(Map.Entry::getKey)
                        .toList())
                .orElse(List.of());

        System.out.println("Departments with max employees: " + departmentsWithMaxEmployees);
        System.out.println("===================================");

        //4. Find the average salary of each department
        Map<String, Double> avrSalOfEachDept = empList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        for (Map.Entry<String, Double> entry : avrSalOfEachDept.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("===================================");

        //5. Find the youngest male employee in the "IT" department
        Optional<Employee> youngEmp = empList.stream().filter(x -> x.getDepartment().equalsIgnoreCase("it")).min(Comparator.comparingInt(Employee::getAge));
        Employee emp = youngEmp.get();
        System.out.println(emp.getName());
        System.out.println("====================================");

        //6. Find the senior-most employee (based on earliest dateOfJoining)
        Employee oldEmp = empList.stream().min(Comparator.comparingInt(Employee::getDateOfJoining)).orElse(null);
        System.out.println("Old employee name:-" + oldEmp.getName() + ", Date of Joining :-" + oldEmp.getDateOfJoining());
        System.out.println("====================================");

        //Important:-
        //7. Find the second highest salary in the organization
        Employee secMaxSal = empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst().orElse(null);
        System.out.println(secMaxSal.getName() + ", Salary :-" + secMaxSal.getSalary());
        System.out.println("=====================================");

        //8. List employees who joined after 2015
        List<Employee> empList2015 = empList.stream().filter(x -> x.getDateOfJoining() > 2015).collect(Collectors.toList());
        for (Employee e : empList2015) {
            System.out.println(e);
        }
        System.out.println("=====================================");

        //Important
        //9. Find the highest-paid employee in each department
        Map<String, Optional<Employee>> highestPaidEmp =  empList.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.maxBy(Comparator.comparingDouble(Employee :: getSalary))));
        for(Map.Entry<String, Optional<Employee>> entry : highestPaidEmp.entrySet()){
            System.out.println(entry.getKey()+" - "+entry.getValue().get());
        }
        System.out.println("=======================================");

        //10. Get employees grouped by age ≤ 25 and > 25
        Map<Boolean, List<Employee>> partitionemp = empList.stream().collect(Collectors.partitioningBy(x -> x.getAge() > 25));
        System.out.println("Employee above 25 years age :" + partitionemp.get(true));
        System.out.println("Employee below 25 years age :" + partitionemp.get(false));

    }
}
