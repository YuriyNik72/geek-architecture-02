package composite;

import java.util.List;

public class Designer implements Employee{

    private float salary;
    private final String name;
    private List<String> roles;

    public Designer(float salary, String name) {
        this.salary = salary;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public float getSalary() {
        return salary;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }
}
