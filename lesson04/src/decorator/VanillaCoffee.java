package decorator;

public class VanillaCoffee implements Coffee {

    private Coffee coffee;

    public VanillaCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public int getCost() {
        return coffee.getCost() + 3;
    }

    public String getDescription() {
        return coffee.getDescription() + ", vanilla";
    }
}
