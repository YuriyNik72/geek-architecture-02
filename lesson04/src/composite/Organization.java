package composite;

import java.util.ArrayList;
import java.util.List;

public class Organization {

    List<Employee> employees;

    public Organization() {
        this.employees = new ArrayList<>();
    }

    public Organization(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public float getNetSalaries() {
        float netSalary = 0.0F;

        for (Employee employee : employees) {
            netSalary += employee.getSalary();
        }
        return netSalary;
    }
}
