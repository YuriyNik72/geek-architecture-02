package composite;

public class Composite {
    public static void main(String[] args) {

        Employee john = new Developer(12000, "John Doe");
        Employee jane = new Designer(15000, "Jane Doe");


        Organization organization = new Organization();
        organization.addEmployee(john);
        organization.addEmployee(jane);

        System.out.println("Net salaries: " + organization.getNetSalaries()); // Net Salaries: 27000
    }
}
