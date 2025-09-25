public class Employee {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String department;
    private double salary;
    private int dateOfJoining;

    //Constructor :-
    public Employee(int id, String name, String gender, int age, String department, double salary, int dateOfJoining) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.department = department;
        this.salary = salary;
        this.dateOfJoining = dateOfJoining;
    }

    //Setter and Getter :-

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(int dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
    //toString :-


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", dateOfJoining=" + dateOfJoining +
                '}';
    }
}
