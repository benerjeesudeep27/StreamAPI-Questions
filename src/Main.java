import java.awt.image.ImageProducer;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> empList = Arrays.asList(
                new Employee(101, "Amit Sharma", "Male", 28, "IT", 65000, 2015),
                new Employee(102, "Priya Verma", "Female", 32, "HR", 58000, 2010),
                new Employee(103, "Ravi Kumar", "Male", 45, "Finance", 72000, 2020),
                new Employee(104, "Sneha Joshi", "Female", 29, "Marketing", 60000, 2016),
                new Employee(105, "Arjun Mehta", "Male", 35, "Sales", 55000,2012),
                new Employee(106, "Neha Singh", "Female", 26, "IT", 67000, 2013),
                new Employee(107, "Karan Patel", "Male", 40, "Operations", 70000, 2017),
                new Employee(108, "Divya Nair", "Female", 31, "HR", 59000,2023),
                new Employee(109, "Manish Gupta", "Male", 38, "Finance", 75000, 2022),
                new Employee(110, "Ritika Das", "Female", 27, "Marketing", 61000,2011),
                new Employee(111, "Siddharth Roy", "Male", 30, "Sales", 56000, 2014),
                new Employee(112, "Anjali Jain", "Female", 33, "IT", 68000, 2016),
                new Employee(113, "Vikram Chauhan", "Male", 42, "Operations", 71000, 2021),
                new Employee(114, "Meera Iyer", "Female", 25, "HR", 57000, 2023),
                new Employee(115, "Rajesh Thakur", "Male", 37, "Finance", 74000, 2009),
                new Employee(113, "Purbasa Banerjee", "Female", 32, "Operations", 51000, 2020)
        );
        //Ques1.  How many male and female employees are there in the organization ?
        long maleCount = empList.stream().filter(x -> x.getGender().equalsIgnoreCase("male")).count();
        long femaleCount = empList.stream().filter(x -> x.getGender().equalsIgnoreCase("female")).count();
        System.out.println(maleCount);
        System.out.println(femaleCount);
        System.out.println("--------------------------------------------");

        //Ques2. Print the name of all departments in the organization ?
        empList.stream().map(x -> x.getDepartment()).distinct().forEach(System.out::println);
        System.out.println("--------------------------------------------");

        // Important
        //Ques3. What is the average age of male and female employees ?
        double avrAge = empList.stream().mapToInt(x -> x.getAge()).average().orElse(0.0);
        System.out.println("Average Age :- "+avrAge);
        System.out.println("--------------------------------------------");

        // Important
        //Ques4. Get the details of highest paid employee in the organization ?
        Optional<Employee> highestSal =  empList.stream().max(Comparator.comparingDouble(Employee :: getSalary));
        System.out.println("Highest Salary :- "+highestSal.get());
        System.out.println("--------------------------------------------");

        //Ques5. Get the names of all employees who have joined after 2015 ?
        empList.stream().filter(x -> x.getDateOfJoining() > 2015).forEach(x -> System.out.println(x.getName()));
        System.out.println("--------------------------------------------");

        //Most Important
        //Ques6. Count the number of employees in each department ?
        Map<String, Long> empCount =empList.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.counting()));
        System.out.println(empCount);
        System.out.println("--------------------------------------------");

        //Ques7. What is the average salary of each department ?
        Map<String, Double> empSalary = empList.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(empSalary);
        System.out.println("--------------------------------------------");

        //Most Important
        //Ques8. Get the details of youngest male employee in the IT department ?
        Optional<Employee> youngEmp = empList.stream().filter(x -> x.getDepartment().equals("IT") && x.getGender().equals("Male")).min(Comparator.comparing(Employee :: getAge));
        System.out.println(youngEmp.get());
        System.out.println("--------------------------------------------");

        //Ques9. Who has the most working experience in the organization ?
        Optional<Employee> oldEmpl = Optional.of(empList.stream().min(Comparator.comparing(Employee::getDateOfJoining)).orElse(null));
        System.out.println(oldEmpl.get());
        System.out.println("--------------------------------------------");

        //Ques10. How many male and female employees are there in the Sales team ?
        Map<String, Long> oprEmp = empList.stream().filter(x -> x.getDepartment().equals("Operations")).collect(Collectors.groupingBy(Employee :: getGender, Collectors.counting()));
        System.out.println(oprEmp);
        System.out.println("--------------------------------------------");

        //Ques11.  What is the average salary of male and female employees ?
        Map<String, Double> genSal = empList.stream().collect(Collectors.groupingBy(Employee :: getGender, Collectors.averagingDouble(Employee ::getSalary)));
        System.out.println(genSal);
        System.out.println("--------------------------------------------");

        //Most Important
        //Ques12. List down the names of all employees in each department ?
        Map<String, List<String>> deptEmp = empList.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.mapping(Employee :: getName, Collectors.toList())));
        System.out.println(deptEmp);
        System.out.println("--------------------------------------------");

        //Ques13.  What is the average salary and total salary of the whole organization ?
        double avrSalary = empList.stream().mapToDouble(Employee :: getSalary).average().orElse(0.0);
        double totalSla = empList.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println(avrSalary);
        System.out.println(totalSla);
        System.out.println("--------------------------------------------");

        //Ques14.  Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years ?
        Map<Boolean, List<Employee>> list = empList.stream().collect(Collectors.partitioningBy(x -> x.getAge() <= 25));
        System.out.println("--------------------------------------------");
        System.out.println("Below 25 yrs : "+list.get(true));
        System.out.println("Above 25 yrs : "+list.get(false));

        //Ques15. Sort the employees by there name
        empList.stream().map(x -> x.getName()).sorted().forEach(System.out::println);
        System.out.println("--------------------------------------------");

        //Ques16. Reverse Sort the employees by there name
        empList.stream().map(x -> x.getName()).sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }


}