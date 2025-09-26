import java.util.*;
import java.util.stream.*;

public class Main_3 {
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

        //1. Find the names of all employees who earn more than the average salary
        double avrSalary = empList.stream().mapToDouble(Employee :: getSalary).average().orElse(0.0);
        List<Employee> listOfEmp = empList.stream().filter(x -> x.getSalary() > avrSalary).collect(Collectors.toList());
        System.out.println("List of employees having salary greater than average salary :-");
        int i = 1;
        for(Employee e : listOfEmp){
            System.out.println(i+++". "+e.getName());
        }
        System.out.println("=================================");

        //2. Get the department names sorted in ascending order of employee count
        List<String> sortedDept = empList.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.comparingByValue())
                .map(Map.Entry:: getKey).toList();
        int j = 1;
        for(String str : sortedDept){
            System.out.println(j+++". "+str);
        }

        //Other Way :-
        System.out.println("=================================");
        List<Map.Entry<String, Long>> sortedMap_2 = empList.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
        sortedMap_2.forEach(entry -> System.out.println(entry.getKey()+" - "+entry.getValue()));
        System.out.println("=================================");

        //3. Find the oldest employee in each department
        Map<String, Optional<Employee>> oldEmpDept =  empList.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.maxBy(Comparator.comparingInt(Employee :: getDateOfJoining))));
        for(Map.Entry<String, Optional<Employee>> entry : oldEmpDept.entrySet()){
            System.out.println(entry.getKey()+" - "+entry.getValue().get());
        }
        System.out.println("=================================");

        //4. Get a list of employees sorted by department and then by name
        List<Employee> sortedDeptName =  empList.stream().sorted(Comparator.comparing(Employee :: getDepartment).thenComparing(Employee::getName)).toList();
        for(Employee e : sortedDeptName){
            System.out.println(e);
        }
        System.out.println("=================================");

        //5. Find the employee(s) who joined most recently
        Employee emp = empList.stream().max(Comparator.comparing(Employee :: getDateOfJoining)).orElse(null);
        System.out.println(emp);
        System.out.println("=================================");

        //6. Find the top 3 highest-paid employees in the organization
        List<Employee> top3Emp = empList.stream().sorted(Comparator.comparingDouble(Employee :: getSalary).reversed()).limit(3).toList();
        for(Employee e : top3Emp){
            System.out.println(e);
        }
        System.out.println("=================================");

        //7. Find the highest-paid employee in each department who joined after 2015
        Map<String, Optional<Employee>> deptTopEarner = empList.stream().filter(x -> x.getDateOfJoining() > 2015).collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.maxBy(Comparator.comparingDouble(Employee :: getSalary))));
        for(Map.Entry<String, Optional<Employee>> entry : deptTopEarner.entrySet()){
            System.out.println(entry.getKey()+" - "+entry.getValue().get());
        }
        System.out.println("=================================");

        //8. Create a map of department → list of employee names
        Map<String, List<String>> deptEmpList = empList.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
        for(Map.Entry<String, List<String>> entry : deptEmpList.entrySet()){
            System.out.println(entry.getKey()+" - "+entry.getValue());
        }
        System.out.println("=================================");

        //Important :-
        //9. Get the salary statistics (count, min, max, sum, average)
        DoubleSummaryStatistics stats = empList.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(stats);
        System.out.println("=================================");

        //10. Partition employees based on gender (male vs female)
        Map<Boolean, List<Employee>> partitionList = empList.stream().collect(Collectors.partitioningBy(x -> x.getGender().equals("Male")));
        System.out.println(partitionList.get(true));
        System.out.println(partitionList.get(false));
        System.out.println("=================================");

        //11. Find the employee with the nth highest salary (e.g., 3rd highest)
        Employee thirdHighestSal = empList.stream().sorted(Comparator.comparingDouble(Employee :: getSalary).reversed()).skip(2).findFirst().orElse(null);
        System.out.println(thirdHighestSal);
        System.out.println("=================================");

        //12. Find the youngest female employee in the organization
        Employee youngEmp =  empList.stream().filter(x -> x.getGender().equals("Female")).min(Comparator.comparingInt(Employee::getAge)).orElse(null);
        System.out.println(youngEmp);
        System.out.println("=================================");

        //13. Get the department with the lowest average salary
        double avgsal = empList.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);
        List<String> arvSalDept = empList.stream().filter(x -> x.getSalary() < avgsal).map(Employee::getDepartment).distinct().toList();
        int k = 1;
        for(String str : arvSalDept){
            System.out.println(k+++". "+str);
        }
        System.out.println("=================================");

        //14. Find employees grouped by decade of joining (2000s, 2010s, etc.)
        Map<Integer, List<Employee>> decadeEmpList = empList.stream().collect(Collectors.groupingBy(x -> (x.getDateOfJoining()/10)*10));
        for(Map.Entry<Integer, List<Employee>> entry : decadeEmpList.entrySet()){
            System.out.println(entry.getKey()+" - "+entry.getValue());
        }

    }
}
